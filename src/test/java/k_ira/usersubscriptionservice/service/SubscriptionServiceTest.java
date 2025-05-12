package k_ira.usersubscriptionservice.service;

import k_ira.usersubscriptionservice.entity.Subscription;
import k_ira.usersubscriptionservice.entity.User;
import k_ira.usersubscriptionservice.repository.SubscriptionRepository;
import k_ira.usersubscriptionservice.request.SubscriptionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private UserService userService;

    private User user;

    @InjectMocks
    private SubscriptionService subscriptionService;

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
    void addSubscriptionSuccess() {
        SubscriptionRequest request = new SubscriptionRequest("Netflix", 1L);

        when(userService.findUser(1L)).thenReturn(user);
        when(subscriptionRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Subscription result = subscriptionService.addSubscription(request);

        assertEquals("Netflix", result.getTitle());
    }

    @Test
    void getUserSubscriptionsSuccess() {
        List<Subscription> list = List.of(new Subscription(1L, "YT", user));

        when(userService.findUser(1L)).thenReturn(user);
        when(subscriptionRepository.findAllByUserId(1L)).thenReturn(list);

        List<Subscription> result = subscriptionService.getUserSubscriptions(1L);

        assertEquals(1, result.size());
    }

    @Test
    void deleteSubscriptionSuccess() {
        subscriptionService.deleteSubscription(1L);
        verify(subscriptionRepository).deleteById(1L);
    }

    @Test
    void getTopSubscriptions_returnsListOfTitles() {
        List<Object[]> mockResult = List.of(
                new Object[]{"YouTube Premium", 5L},
                new Object[]{"Netflix", 4L},
                new Object[]{"Spotify", 3L}
        );

        when(subscriptionRepository.findTopSubscriptions(PageRequest.of(0, 3)))
                .thenReturn(mockResult);

        List<String> result = subscriptionService.getTopSubscriptions();

        assertEquals(List.of("YouTube Premium", "Netflix", "Spotify"), result);
    }
}

