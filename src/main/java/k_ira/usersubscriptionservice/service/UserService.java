package k_ira.usersubscriptionservice.service;

import jakarta.persistence.EntityNotFoundException;
import k_ira.usersubscriptionservice.entity.User;
import k_ira.usersubscriptionservice.repository.UserRepository;
import k_ira.usersubscriptionservice.request.UserCreateRequest;
import k_ira.usersubscriptionservice.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .name(userCreateRequest.name())
                .email(userCreateRequest.email())
                .build();
       return userRepository.save(user);
    }

    public User getUser (long id) {
      return   findUser(id);
    }

    public User updateUser (UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userUpdateRequest.id())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (userUpdateRequest.name() != null) {
            user.setName(userUpdateRequest.name());
        }
        if (userUpdateRequest.email() != null) {
            user.setEmail(userUpdateRequest.email());
        }
       return userRepository.save(user);
    }

    public void deleteUser(long userId) {
        userRepository.delete(findUser(userId));
    }

    public User findUser (long userId) {
        return   userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("User not found"));
    }
}
