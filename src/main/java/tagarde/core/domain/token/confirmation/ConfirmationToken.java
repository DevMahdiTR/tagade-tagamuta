package tagarde.core.domain.token.confirmation;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tagarde.core.domain.token.Token;
import tagarde.core.domain.auth.user.UserEntity;


@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "confirmation_tokens")
public class ConfirmationToken extends Token {

    public ConfirmationToken(Long id, String token, boolean revoked, boolean expired, UserEntity userEntity) {
        super(id, token, revoked, expired, userEntity);
    }

    public static ConfirmationTokenBuilder builder() {
        return new ConfirmationTokenBuilder();
    }
}
