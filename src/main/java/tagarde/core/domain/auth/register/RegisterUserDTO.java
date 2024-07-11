package tagarde.core.domain.auth.register;


import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RegisterUserDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    @Pattern(regexp = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$", message = "Invalid email address. Please enter a valid email.")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Invalid password. Passwords must be at least 8 characters long and include at least one uppercase letter, " +
                    "one lowercase letter, one digit, and one special character.")
    private String password;

}
