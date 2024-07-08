package tagarde.core.service.auth;


import org.jetbrains.annotations.NotNull;
import tagarde.core.domain.auth.LogInDTO;
import tagarde.core.domain.auth.LogInResponseDTO;
import tagarde.core.domain.auth.RegisterDTO;
import tagarde.core.domain.auth.RegisterResponseDTO;
import tagarde.core.utility.CustomerResponse;

public interface AuthService {

    CustomerResponse<RegisterResponseDTO> register(@NotNull final RegisterDTO registerDTO);
    CustomerResponse<LogInResponseDTO> logIn(@NotNull final LogInDTO logInDTO);
    CustomerResponse<String> validateToken(final String token);
    String confirmAccount(final String confirmationToken);


}
