package shared.hub.auth.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import shared.hub.auth.dto.ApiResponse;
import shared.hub.auth.dto.CreateUserRequest;
import shared.hub.auth.dto.RegisterUserRequest;
import shared.hub.auth.dto.UserResponse;

public interface AppUserService {

    ResponseEntity<ApiResponse<UserResponse>> createUser(CreateUserRequest request);

    ResponseEntity<ApiResponse<UserResponse>> registerUser(RegisterUserRequest request);

    ResponseEntity<ApiResponse<Page<UserResponse>>> getUsers(Pageable pageable);

    ResponseEntity<ApiResponse<UserResponse>> getUser(String username);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
}
