package shared.hub.auth.mapper;

import org.springframework.beans.BeanUtils;
import shared.hub.auth.dto.CreateUserRequest;
import shared.hub.auth.dto.RegisterUserRequest;
import shared.hub.auth.dto.UserResponse;
import shared.hub.auth.model.entity.AppUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserMapper {
    public static UserResponse mapToUserResponse(AppUser user) {
        if (Objects.isNull(user)) return null;
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    public static List<UserResponse> mapToListUserResponse(List<AppUser> users) {
        List<UserResponse> response = new ArrayList<>();
        for (AppUser user : users) {
            response.add(mapToUserResponse(user));
        }
        return response;
    }

    public static AppUser mapToUserEntity(RegisterUserRequest request) {
        if (Objects.isNull(request)) return null;
        return AppUser.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .active(true)
                .verified(false)
                .image("")
                .build();
    }

    public static AppUser mapToUserEntity(CreateUserRequest request) {
        if (Objects.isNull(request)) return null;
        return AppUser.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .active(true)
                .verified(false)
                .image("")
                .build();

    }
}
