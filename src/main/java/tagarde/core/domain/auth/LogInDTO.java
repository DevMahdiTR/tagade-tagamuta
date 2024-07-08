package tagarde.core.domain.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LogInDTO {

    @Pattern(regexp = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$", message = "Invalid email address. Please enter a valid email.")
    private String email;
    @NotBlank
    private String password;
}
