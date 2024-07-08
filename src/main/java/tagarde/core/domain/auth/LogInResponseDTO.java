package tagarde.core.domain.auth;

import lombok.Builder;
import lombok.Data;
import tagarde.core.domain.auth.user.UserEntityDTO;

@Data
@Builder
public class LogInResponseDTO {
    private UserEntityDTO userEntityDTO;
    private String accessToken;
    private String refreshToken;
}
