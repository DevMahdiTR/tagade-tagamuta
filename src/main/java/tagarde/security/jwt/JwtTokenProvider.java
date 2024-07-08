package tagarde.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tagarde.core.exceptions.custom.InvalidTokenException;
import tagarde.security.utility.CustomUserDetailsService;
import tagarde.security.utility.SecurityConstants;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Component
public class JwtTokenProvider {

    private final CustomUserDetailsService customUserDetailsService;

    public JwtTokenProvider(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    public static Key getSignInKey(String secretCode) {
        byte[] keyBytes = Decoders.BASE64.decode(secretCode);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey(SecurityConstants.JWT_ACCESS_SECRET))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            if (expiration == null) {
                throw new InvalidTokenException("Expiration claim missing in JWT token");
            }

            boolean isExpired = expiration.before(new Date());
            return !isExpired;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("Expired or invalid JWT token");
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(extractEmailFromJwt(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public <T> T extractClaim(String token, @NotNull Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey(SecurityConstants.JWT_ACCESS_SECRET))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new JwtException("Token has expired. Please log in again.");
        }
    }

    public String extractEmailFromJwt(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
