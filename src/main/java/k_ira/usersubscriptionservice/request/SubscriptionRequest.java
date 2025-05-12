package k_ira.usersubscriptionservice.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubscriptionRequest(
        @NotBlank String title,
        @NotNull long userId
) {
}
