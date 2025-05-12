package k_ira.usersubscriptionservice.service;

import jakarta.persistence.EntityNotFoundException;
import k_ira.usersubscriptionservice.entity.User;
import k_ira.usersubscriptionservice.repository.UserRepository;
import k_ira.usersubscriptionservice.request.UserCreateRequest;
import k_ira.usersubscriptionservice.request.UserUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private User user;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("Test")
                .email("test@test.com")
                .subscriptions(null)
                .build();
    }

    @Test
    void saveUserSuccess() {
        UserCreateRequest request = new UserCreateRequest("Anna", "anna@test.com");
        User user = User.builder().name("Anna").email("anna@test.com").build();

        when(userRepository.save(any())).thenReturn(user);

        User result = userService.saveUser(request);

        assertEquals("Anna", result.getName());
    }

    @Test
    void getUserFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUser(1L);

        assertEquals("Test", result.getName());
    }

    @Test
    void updateUserSuccess() {
        User existing = user;
        UserUpdateRequest update = new UserUpdateRequest(1L, "New", "new@test.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(any())).thenReturn(existing);

        User result = userService.updateUser(update);

        assertEquals("New", result.getName());
        assertEquals("new@test.com", result.getEmail());
    }

    @Test
    void deleteUserSuccess() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void findUserSuccess() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findUser(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test", result.getName());
        assertEquals("test@test.com", result.getEmail());
    }

    @Test
    void findUserThrows() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findUser(2L));
    }
}