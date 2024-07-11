package tagarde.core.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import tagarde.core.service.token.TokenService;
import tagarde.core.domain.token.access.AccessToken;
import tagarde.security.jwt.JwtTokenProvider;

@Service
public class LogoutService implements LogoutHandler {

    private final TokenService<AccessToken> tokenService;
    private final JwtTokenProvider jwtTokenProvider;

    public LogoutService(TokenService<AccessToken> tokenService, JwtTokenProvider jwtTokenProvider) {
        this.tokenService = tokenService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String jwt = jwtTokenProvider.resolveToken(request);
        AccessToken storedToken = tokenService.findByToken(jwt);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenService.save(storedToken);
        }

    }
}

