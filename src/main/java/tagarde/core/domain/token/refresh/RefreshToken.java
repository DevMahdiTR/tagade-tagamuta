package tagarde.core.domain.token.refresh;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tagarde.core.domain.token.Token;
import tagarde.core.domain.auth.user.UserEntity;

@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken extends Token {

    public RefreshToken(Long id, String token, boolean revoked, boolean expired, UserEntity userEntity) {
        super(id, token, revoked, expired, userEntity);
    }


    public static RefreshTokenBuilder builder() {
        return new RefreshTokenBuilder();
    }
}
