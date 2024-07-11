package tagarde.core.service.auth;


import org.jetbrains.annotations.NotNull;
import tagarde.core.domain.auth.login.LogInDTO;
import tagarde.core.domain.auth.login.LogInResponseDTO;
import tagarde.core.domain.auth.register.RegisterUserDTO;
import tagarde.core.utility.CustomerResponse;

public interface AuthService {

    CustomerResponse<String> register(@NotNull final String role,@NotNull final RegisterUserDTO registerUserDTO);
    CustomerResponse<LogInResponseDTO> logIn(@NotNull final LogInDTO logInDTO);
    CustomerResponse<String> validateToken(final String token);
    String confirmAccount(final String confirmationToken);


}
