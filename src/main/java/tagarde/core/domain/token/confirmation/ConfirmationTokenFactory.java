package tagarde.core.domain.token.confirmation;

import tagarde.core.domain.token.TokenFactory;
import tagarde.core.domain.token.TokenGenerator;
import tagarde.core.domain.auth.user.UserEntity;

public class ConfirmationTokenFactory extends TokenFactory<ConfirmationToken> {

    @Override
    protected ConfirmationToken create(UserEntity userEntity) {
        return ConfirmationToken
                .builder()
                .token(getTokenGenerator().generateToken(userEntity))
                .expired(false)
                .revoked(false)
                .userEntity(userEntity)
                .build();
    }

    @Override
    protected TokenGenerator getTokenGenerator() {
        return new ConfirmationTokenGenerator();
    }
}
