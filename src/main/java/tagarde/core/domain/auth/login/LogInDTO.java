package tagarde.core.domain.auth.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import tagarde.core.exceptions.validator.CustomValidation;

@Data
public class LogInDTO {

    @CustomValidation(regex = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:[-]?[a-zA-Z0-9]+)*(?:\\.[a-zA-Z]{2,})+$")
    private String email;
    @CustomValidation(message = "Passwords must be at least 8 characters long and include at least one uppercase letter, " +
            "one lowercase letter, one digit, and one special character.",regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;
}
