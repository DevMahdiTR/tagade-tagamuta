package tagarde.core.repository.token;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tagarde.core.domain.token.confirmation.ConfirmationToken;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends TokenRepository<ConfirmationToken> {


}
