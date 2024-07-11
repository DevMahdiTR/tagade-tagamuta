package tagarde.core.domain.auth.register;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tagarde.core.domain.auth.user.UserEntityDTO;

@Builder
@Getter
@Setter
public class RegisterResponseDTO {
    private UserEntityDTO userEntityDTO;
    private String confirmationToken;
    private String refreshToken;
}
