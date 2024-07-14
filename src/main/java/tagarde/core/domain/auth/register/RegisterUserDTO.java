package tagarde.core.domain.auth.register;


import lombok.Getter;
import lombok.Setter;
import tagarde.core.utility.validator.CustomValidation;


@Setter
@Getter
public class RegisterUserDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    @CustomValidation(regex = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:[-]?[a-zA-Z0-9]+)*(?:\\.[a-zA-Z]{2,})+$")
    private String email;

    @CustomValidation(regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Passwords must be at least 8 characters long and include at least one uppercase letter, " +
                    "one lowercase letter, one digit, and one special character.")
    private String password;

}
