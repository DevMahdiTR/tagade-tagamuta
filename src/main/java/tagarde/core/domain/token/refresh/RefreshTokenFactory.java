package tagarde.core.domain.token.refresh;

import tagarde.core.domain.auth.user.UserEntity;
import tagarde.core.domain.token.TokenFactory;
import tagarde.core.domain.token.TokenGenerator;

public class RefreshTokenFactory extends TokenFactory<RefreshToken> {


    @Override
    protected RefreshToken create(UserEntity userEntity) {
        return RefreshToken
                .builder()
                .token(getTokenGenerator().generateToken(userEntity))
                .expired(false)
                .revoked(false)
                .userEntity(userEntity)
                .build();
    }

    @Override
    protected TokenGenerator getTokenGenerator() {
        return new RefreshTokenGenerator();
    }
}
