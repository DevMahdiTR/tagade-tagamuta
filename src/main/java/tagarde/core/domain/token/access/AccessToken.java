package tagarde.core.domain.token.access;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tagarde.core.domain.token.Token;
import tagarde.core.domain.auth.user.UserEntity;


@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "access_tokens")
public class AccessToken extends Token {

    public AccessToken(Long id, String token, boolean revoked, boolean expired, UserEntity userEntity) {
        super(id, token, revoked, expired, userEntity);
    }

    public static AccessTokenBuilder builder() {
        return new AccessTokenBuilder();
    }


}
