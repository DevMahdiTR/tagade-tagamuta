package tagarde.core.domain.token.access;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tagarde.core.domain.token.TokenGenerator;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.security.jwt.JwtTokenProvider;
import tagarde.security.utility.SecurityConstants;

import java.util.Date;

public class AccessTokenGenerator implements TokenGenerator {
    @Override
    public String generateToken(UserEntity userEntity) {
        String email = userEntity.getEmail();
        Date currentData = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + SecurityConstants.ACCESS_JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentData)
                .setExpiration(expireDate)
                .signWith(JwtTokenProvider.getSignInKey(SecurityConstants.JWT_ACCESS_SECRET), SignatureAlgorithm.HS256)
                .compact();
    }
}
