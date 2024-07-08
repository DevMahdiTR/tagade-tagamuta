package tagarde.core.domain.auth;


import lombok.Builder;
import lombok.Data;
import tagarde.core.domain.auth.user.UserEntityDTO;

@Builder
@Data
public class RegisterResponseDTO {
    private UserEntityDTO userEntityDTO;
    private String confirmationToken;
    private String refreshToken;
}
