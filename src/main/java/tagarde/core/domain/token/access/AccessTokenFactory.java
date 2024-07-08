package tagarde.core.domain.token.access;


import tagarde.core.domain.token.TokenFactory;
import tagarde.core.domain.token.TokenGenerator;
import tagarde.core.domain.auth.user.UserEntity;

public class AccessTokenFactory extends TokenFactory<AccessToken> {


    @Override
    protected AccessToken create(UserEntity userEntity) {
        return AccessToken.
                builder()
                .token(getTokenGenerator().generateToken(userEntity))
                .expired(false)
                .revoked(false)
                .userEntity(userEntity)
                .build();

    }

    @Override
    protected TokenGenerator getTokenGenerator() {
        return new AccessTokenGenerator();
    }
}
