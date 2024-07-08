package tagarde.core.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import tagarde.core.domain.token.Token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
@Transactional(readOnly = true)
public interface TokenRepository<T extends Token> extends JpaRepository<T, Long> {
    default Optional<T> findByToken(String token) {
        return findAll().stream()
                .filter(t -> token.equals(t.getToken()))
                .findFirst();
    }

    default boolean isTokenValidAndExist(String token) {
        return findByToken(token).map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
    }

    default List<T> fetchAllValidTokenByUserId(UUID userId) {
        return findAll().stream()
                .filter(t -> t.getUserEntity().getId().equals(userId) && (!t.isExpired() || !t.isRevoked()))
                .toList();
    }

}
