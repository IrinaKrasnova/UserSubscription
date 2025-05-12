package k_ira.usersubscriptionservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import k_ira.usersubscriptionservice.entity.Subscription;
import k_ira.usersubscriptionservice.request.SubscriptionRequest;
import k_ira.usersubscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Validated
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/subscriptions")
    public ResponseEntity<Subscription> addSubscription(@Valid @RequestBody SubscriptionRequest subscriptionRequest) {
        return ResponseEntity.ok(subscriptionService.addSubscription(subscriptionRequest));
    }

    @GetMapping("/users/{userId}/subscriptions")
    public ResponseEntity<List<Subscription>> getSubscriptions(
            @PathVariable
            @Positive(message = "Subscriptions id must be positive")
            long userId) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userId));
    }

    @DeleteMapping("/subscriptions/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(
            @PathVariable
            @Positive(message = "Subscriptions id must be positive")
            long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subscriptions/top")
    public ResponseEntity<List<String>> getTopSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }
}
