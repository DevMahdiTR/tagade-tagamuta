package tagarde.core.domain.token.refresh;

import tagarde.core.domain.auth.user.UserEntity;
public class RefreshTokenBuilder {
    private Long id;
    private String token;
    private boolean revoked, expired;
    private UserEntity userEntity;


    public RefreshTokenBuilder id(final Long id) {
        this.id = id;
        return this;
    }

    public RefreshTokenBuilder token(final String token) {
        this.token = token;
        return this;
    }

    public RefreshTokenBuilder revoked(final boolean revoked) {
        this.revoked = revoked;
        return this;
    }

    public RefreshTokenBuilder expired(final boolean expired) {
        this.expired = expired;
        return this;
    }

    public RefreshTokenBuilder userEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public RefreshToken build() {
        return new RefreshToken(id, token, revoked, expired, userEntity);
    }
}
