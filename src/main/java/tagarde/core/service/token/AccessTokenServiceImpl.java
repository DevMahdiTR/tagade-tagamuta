package tagarde.core.service.token;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import tagarde.core.exceptions.custom.DatabaseException;
import tagarde.core.exceptions.custom.ResourceNotFoundException;
import tagarde.core.domain.token.Token;
import tagarde.core.domain.token.access.AccessToken;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.core.repository.token.AccessTokenRepository;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class AccessTokenServiceImpl implements TokenService<AccessToken> {
    private final AccessTokenRepository accessTokenRepository;

    public AccessTokenServiceImpl(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    public AccessToken saveAndFlush(AccessToken token) {
        try {
            log.info("Database Request to Access Token: {}", token);
            final AccessToken accessToken = accessTokenRepository.saveAndFlush(token);
            log.info("{} saved successfully.", accessToken);
            return accessToken;
        } catch (Exception e) {
            log.error("Error saving Access Token: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    @Override
    public AccessToken save(AccessToken token) {
        try {
            log.info("Database Request to Access Token: {}", token);
            final AccessToken accessToken = accessTokenRepository.save(token);
            log.info("{} saved successfully.", accessToken);
            return accessToken;
        } catch (Exception e) {
            log.error("Error saving Access Token: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    @Override
    public List<AccessToken> saveAll(List<AccessToken> tokens) {
        try {
            log.info("Database Request to save tokens:");
            for (AccessToken at : tokens) {
                log.info("{}", at);
            }
            final List<AccessToken> savedTokens = accessTokenRepository.saveAll(tokens);
            log.info("saved tokens successfully.");
            for (Token t : tokens) {
                log.info("{}", t);
            }
            return savedTokens;
        } catch (Exception e) {
            log.error("Error saving Token: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    @Override
    public List<AccessToken> fetchAllValidTokenByUserId(UUID userId) {
        return accessTokenRepository.fetchAllValidTokenByUserId(userId);
    }

    @Override
    public AccessToken findByToken(String token) {
        return accessTokenRepository.findByToken(token).orElseThrow(
                () -> new ResourceNotFoundException("The Token could not be found.")
        );
    }

    @Override
    public void revokeAllUserToken(@NotNull final UserEntity userEntity) {
        var validUserTokens = fetchAllValidTokenByUserId(userEntity.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        accessTokenRepository.saveAll(validUserTokens);
    }

    @Override
    public boolean isTokenValidAndExist(String token) {
        return accessTokenRepository.isTokenValidAndExist(token);
    }
}
