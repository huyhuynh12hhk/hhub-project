package shared.hub.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "{username.required}")
    @Size(min = 3, max = 20, message = "{username.size}")
    public String username;
    @Email(message = "Invalid email")
    public String email;
    @NotBlank(message = "{password.required}")
    @Size(min = 6, message = "{password.size}")
    public String password;
}
