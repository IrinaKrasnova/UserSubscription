package k_ira.usersubscriptionservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserUpdateRequest(
        @NotNull Long id,
        String name,
        @Email String email
) {
}
