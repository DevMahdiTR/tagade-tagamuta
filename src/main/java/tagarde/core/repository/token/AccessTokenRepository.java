package tagarde.core.repository.token;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tagarde.core.domain.token.access.AccessToken;

@Repository
@Transactional(readOnly = true)
public interface AccessTokenRepository extends TokenRepository<AccessToken> {

}
