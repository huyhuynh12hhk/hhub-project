package shared.hub.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import shared.hub.auth.dto.ApiResponse;
import shared.hub.auth.dto.CreateUserRequest;
import shared.hub.auth.dto.UserResponse;
import shared.hub.auth.service.AppUserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final AppUserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUsers(pageable);
    }

    @GetMapping("/{user}")
    public ResponseEntity<ApiResponse<UserResponse>> get(@PathVariable("user") String username) {

        return userService.getUser(username);
    }

    @GetMapping("/exist")
    public boolean checkUsername(
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "") String email
    ) {

        if (!StringUtils.isEmpty(username)) return userService.checkUsernameExists(username);
        if (!StringUtils.isEmpty(username)) return userService.checkEmailExists(email);

        return false;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(@RequestBody CreateUserRequest request) {

        return userService.createUser(request);
    }
}
