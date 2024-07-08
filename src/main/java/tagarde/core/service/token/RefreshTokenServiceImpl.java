package tagarde.core.service.token;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import tagarde.core.exceptions.custom.DatabaseException;
import tagarde.core.exceptions.custom.ResourceNotFoundException;
import tagarde.core.domain.token.Token;
import tagarde.core.domain.token.refresh.RefreshToken;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.core.repository.token.RefreshTokenRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RefreshTokenServiceImpl implements TokenService<RefreshToken> {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken saveAndFlush(RefreshToken token) {
        try {
            log.info("Database Request to Refresh Token: {}", token);
            final RefreshToken refreshToken = refreshTokenRepository.saveAndFlush(token);
            log.info("{} saved successfully.", refreshToken);
            return refreshToken;
        } catch (Exception e) {
            log.error("Error saving Refresh  Token: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    @Override
    public RefreshToken save(RefreshToken token) {
        try {
            log.info("Database Request to Refresh Token: {}", token);
            final RefreshToken refreshToken = refreshTokenRepository.save(token);
            log.info("{} saved successfully.", refreshToken);
            return refreshToken;
        } catch (Exception e) {
            log.error("Error saving Refresh  Token: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    @Override
    public List<RefreshToken> saveAll(List<RefreshToken> tokens) {
        try {
            log.info("Database Request to save tokens:");
            for (RefreshToken at : tokens) {
                log.info("{}", at);
            }
            final List<RefreshToken> savedTokens = refreshTokenRepository.saveAll(tokens);
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
    public List<RefreshToken> fetchAllValidTokenByUserId(UUID userId) {
        return refreshTokenRepository.fetchAllValidTokenByUserId(userId);
    }

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow(
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
        refreshTokenRepository.saveAll(validUserTokens);
    }

    @Override
    public boolean isTokenValidAndExist(String token) {
        return refreshTokenRepository.isTokenValidAndExist(token);
    }
}
