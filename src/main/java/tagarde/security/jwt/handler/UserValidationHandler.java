package tagarde.security.jwt.handler;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import tagarde.security.jwt.JwtTokenProvider;
import tagarde.security.utility.CustomUserDetailsService;

public class UserValidationHandler extends FilterChainHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    public UserValidationHandler(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public boolean handle(String jwtToken) {
        String userEmail = jwtTokenProvider.extractEmailFromJwt(jwtToken);

        if (userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return false;
        }
        UserDetails userEntity = this.customUserDetailsService.loadUserByUsername(userEmail);

        if (!userEntity.isEnabled()) {
            return false;
        }
        return handleNext(jwtToken);
    }
}
