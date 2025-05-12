package k_ira.usersubscriptionservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import k_ira.usersubscriptionservice.entity.User;
import k_ira.usersubscriptionservice.request.UserCreateRequest;
import k_ira.usersubscriptionservice.request.UserUpdateRequest;
import k_ira.usersubscriptionservice.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/users")
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateRequest user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(
            @PathVariable
            @Positive(message = "User id must be positive")
            @NotNull(message = "User id cannot be null") long userId) {
    return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping
    public ResponseEntity<User> updateUser (@Valid @RequestBody UserUpdateRequest user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable
            @Positive(message = "User id must be positive")
            @NotNull(message = "User id cannot be null") long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
