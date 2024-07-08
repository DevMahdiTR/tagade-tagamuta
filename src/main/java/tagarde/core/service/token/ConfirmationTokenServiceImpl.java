package tagarde.core.service.token;

import lombok.extern.slf4j.Slf4j;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import tagarde.core.exceptions.custom.DatabaseException;
import tagarde.core.exceptions.custom.ResourceNotFoundException;
import tagarde.core.domain.token.Token;
import tagarde.core.domain.token.confirmation.ConfirmationToken;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.core.repository.token.ConfirmationTokenRepository;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ConfirmationTokenServiceImpl implements TokenService<ConfirmationToken> {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }


    @Override
    public ConfirmationToken saveAndFlush(ConfirmationToken token) {
        try {
            log.info("Database Request to Confirmation Token: {}", token);
            final ConfirmationToken confirmationToken = confirmationTokenRepository.saveAndFlush(token);
            log.info("{} saved successfully.", confirmationToken);
            return confirmationToken;
        } catch (Exception e) {
            log.error("Error saving Access Confirmation Token: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    @Override
    public ConfirmationToken save(ConfirmationToken token) {
        try {
            log.info("Database Request to Confirmation Token: {}", token);
            final ConfirmationToken confirmationToken = confirmationTokenRepository.save(token);
            log.info("{} saved successfully.", confirmationToken);
            return confirmationToken;
        } catch (Exception e) {
            log.error("Error saving Access Confirmation Token: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    @Override
    public List<ConfirmationToken> saveAll(List<ConfirmationToken> tokens) {
        try {
            log.info("Database Request to save Confirmation Token:");
            for (var ct : tokens) {
                log.info("{}", ct);
            }
            final List<ConfirmationToken> savedTokens = confirmationTokenRepository.saveAll(tokens);
            log.info("saved Confirmation Tokens successfully.");
            for (Token t : tokens) {
                log.info("{}", t);
            }
            return savedTokens;
        } catch (Exception e) {
            log.error("Error saving Confirmation Tokens: {}", e.getMessage(),e);
            throw new DatabaseException("Error executing database query.");
        }
    }


    @Override
    public List<ConfirmationToken> fetchAllValidTokenByUserId(UUID userId) {
        return confirmationTokenRepository.fetchAllValidTokenByUserId(userId);
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(
                () -> new ResourceNotFoundException("The Confirmation Token could not be found.")
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
        confirmationTokenRepository.saveAll(validUserTokens);
    }

    @Override
    public boolean isTokenValidAndExist(String token) {
        return confirmationTokenRepository.isTokenValidAndExist(token);
    }
}
