package shared.hub.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Username is require")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    public String username;
    @Email(message = "Invalid email")
    public String email;
    @NotBlank(message = "Password is require")
    @Size(min = 6, message = "Password must be at least 6 characters")
    public String password;
    public List<String> roles;
}
