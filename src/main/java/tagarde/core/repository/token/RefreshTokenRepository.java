package tagarde.core.repository.token;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tagarde.core.domain.token.refresh.RefreshToken;

@Repository
@Transactional(readOnly = true)
public interface RefreshTokenRepository extends TokenRepository<RefreshToken> {
}
