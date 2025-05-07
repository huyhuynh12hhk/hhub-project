package shared.hub.auth.dto;

import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String email;
    private String image;
    private String createdDate;
    private boolean active;
}
