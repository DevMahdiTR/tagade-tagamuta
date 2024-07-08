package tagarde.core.domain.token;


import tagarde.core.domain.auth.user.UserEntity;

public interface TokenGenerator {
    String generateToken(UserEntity userEntity);
}
