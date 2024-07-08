package tagarde.core.domain.token.confirmation;

import tagarde.core.domain.auth.user.UserEntity;

public class ConfirmationTokenBuilder {
    private Long id;
    private String token;
    private boolean revoked, expired;
    private UserEntity userEntity;


    public ConfirmationTokenBuilder id(final Long id) {
        this.id = id;
        return this;
    }

    public ConfirmationTokenBuilder token(final String token) {
        this.token = token;
        return this;
    }

    public ConfirmationTokenBuilder revoked(final boolean revoked) {
        this.revoked = revoked;
        return this;
    }

    public ConfirmationTokenBuilder expired(final boolean expired) {
        this.expired = expired;
        return this;
    }

    public ConfirmationTokenBuilder userEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public ConfirmationToken build() {
        return new ConfirmationToken(id, token, revoked, expired, userEntity);
    }
}
