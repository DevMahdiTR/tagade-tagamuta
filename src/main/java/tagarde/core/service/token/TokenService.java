package tagarde.core.service.token;

import org.jetbrains.annotations.NotNull;
import tagarde.core.domain.token.Token;
import tagarde.core.domain.auth.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface TokenService<T extends Token> {

    T saveAndFlush(T token);

    T save(T token);

    List<T> saveAll(final List<T> t);

    List<T> fetchAllValidTokenByUserId(UUID userId);

    T findByToken(String token);

    boolean isTokenValidAndExist(String token);

    void revokeAllUserToken(@NotNull final UserEntity userEntity);



}
