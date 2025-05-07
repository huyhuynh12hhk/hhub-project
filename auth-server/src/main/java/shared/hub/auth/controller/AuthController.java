package shared.hub.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shared.hub.auth.dto.ApiResponse;
import shared.hub.auth.dto.RegisterUserRequest;
import shared.hub.auth.dto.UserResponse;
import shared.hub.auth.service.AppUserService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AppUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @RequestBody RegisterUserRequest request
    ) {
        return userService.registerUser(request);
    }
}
