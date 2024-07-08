package tagarde.core.domain.token.access;


import tagarde.core.domain.auth.user.UserEntity;

public class AccessTokenBuilder {
    private Long id;
    private String token;
    private boolean revoked, expired;
    private UserEntity userEntity;


    public AccessTokenBuilder id(final Long id) {
        this.id = id;
        return this;
    }

    public AccessTokenBuilder token(final String token) {
        this.token = token;
        return this;
    }

    public AccessTokenBuilder revoked(final boolean revoked) {
        this.revoked = revoked;
        return this;
    }

    public AccessTokenBuilder expired(final boolean expired) {
        this.expired = expired;
        return this;
    }

    public AccessTokenBuilder userEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public AccessToken build() {
        return new AccessToken(id, token, revoked, expired, userEntity);
    }
}
