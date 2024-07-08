package tagarde.security.jwt.handler;


import tagarde.core.repository.token.AccessTokenRepository;
import tagarde.security.jwt.JwtTokenProvider;

public class TokenValidationHandler extends FilterChainHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final AccessTokenRepository accessTokenRepository;

    public TokenValidationHandler(JwtTokenProvider jwtTokenProvider, AccessTokenRepository accessTokenRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    public boolean handle(final String jwtToken) {
        if (jwtToken == null || !jwtTokenProvider.validateToken(jwtToken)) {
            return false;
        }

        var isTokenValid = accessTokenRepository.findByToken(jwtToken).map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);

        if (!isTokenValid) {
            return false;
        }

        return handleNext(jwtToken);
    }
}
