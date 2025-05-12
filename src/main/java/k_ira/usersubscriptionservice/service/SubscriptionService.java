package k_ira.usersubscriptionservice.service;

import k_ira.usersubscriptionservice.entity.Subscription;
import k_ira.usersubscriptionservice.entity.User;
import k_ira.usersubscriptionservice.repository.SubscriptionRepository;
import k_ira.usersubscriptionservice.request.SubscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    public Subscription addSubscription(SubscriptionRequest request) {
        User user = userService.findUser(request.userId());
        Subscription subscription = Subscription.builder()
                .title(request.title())
                .user(user)
                .build();
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getUserSubscriptions(long userId) {
        User user = userService.findUser(userId);
        return subscriptionRepository.findAllByUserId(user.getId());
    }

    public void deleteSubscription(long id) {
        subscriptionRepository.deleteById(id);
    }

    public List<String> getTopSubscriptions() {
        return subscriptionRepository.findTopSubscriptions(PageRequest.of(0, 3))
                .stream()
                .map(obj -> (String) obj[0])
                .toList();
    }
}
