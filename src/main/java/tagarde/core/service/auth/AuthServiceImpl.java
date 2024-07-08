package tagarde.core.service.auth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tagarde.core.service.email.EmailService;
import tagarde.core.service.role.RoleService;
import tagarde.core.service.token.TokenService;
import tagarde.core.domain.auth.LogInDTO;
import tagarde.core.domain.auth.LogInResponseDTO;
import tagarde.core.domain.auth.RegisterDTO;
import tagarde.core.domain.auth.RegisterResponseDTO;
import tagarde.core.exceptions.custom.InvalidTokenException;
import tagarde.core.domain.token.access.AccessToken;
import tagarde.core.domain.token.confirmation.ConfirmationToken;
import tagarde.core.domain.token.refresh.RefreshToken;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.security.jwt.JwtTokenProvider;
import tagarde.core.service.user.UserEntityService;
import tagarde.core.utility.CustomerResponse;
import tagarde.core.utility.ThymeleafUtil;

import java.util.Collections;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityService userEntityService;
    private final RoleService roleService;
    private final TokenService<AccessToken> accessTokenTokenService;
    private final TokenService<RefreshToken> refreshTokenTokenService;
    private final TokenService<ConfirmationToken> confirmationTokenTokenService;
    private final EmailService emailService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public CustomerResponse<RegisterResponseDTO> register(@NotNull RegisterDTO registerDTO) {

       /* log.debug("Attempting to register user with email: {}", registerDTO.getEmail());

        if (userEntityService.isEmailRegistered(registerDTO.getEmail())) {
            log.warn("Email already registered: {}", registerDTO.getEmail());
            throw new EmailRegisteredException("Email already in use.");
        }

        final Role role = roleService.fetchRoleByName("CLIENT");

        UserEntity newUser = UserEntity
                .builder()
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .userRole(role)
                .isEnabled(false)
                .build();

        UserEntity savedUser = userEntityService.save(newUser);

        RefreshToken refreshToken = new RefreshTokenFactory().build(savedUser);
        ConfirmationToken confirmationToken = new ConfirmationTokenFactory().build(savedUser);
        confirmationTokenTokenService.saveAndFlush(confirmationToken);
        refreshTokenTokenService.saveAndFlush(refreshToken);

        String activationLink = APIRouters.getConfirmationURL(confirmationToken.getToken());

        log.debug("Sending activation email to: {}", registerDTO.getEmail());
        emailService.sendMail(
                registerDTO.getEmail(),
                "Activating your account.",
                "email-confirmation",
                Map.of(
                        "name", registerDTO.getEmail(),
                        "link", activationLink
                )
        );

        final RegisterResponseDTO registerResponseDTO = RegisterResponseDTO
                .builder()
                .userEntityDTO(userEntityService.mapper(savedUser))
                .confirmationToken(confirmationToken.getToken())
                .refreshToken(refreshToken.getToken())
                .build();

        log.info("User registered successfully: {}", registerDTO.getEmail());
        return new CustomerResponse<>(registerResponseDTO, HttpStatus.CREATED);*/
        return null;
    }

    @Override
    public CustomerResponse<LogInResponseDTO> logIn(@NotNull LogInDTO logInDTO) {
       /* log.debug("Attempting to log in user with email: {}", logInDTO.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        logInDTO.getEmail(),
                        logInDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity user = userEntityService.getUserByEmail(logInDTO.getEmail());

        accessTokenTokenService.revokeAllUserToken(user);
        refreshTokenTokenService.revokeAllUserToken(user);

        AccessToken accessToken = new AccessTokenFactory().build(user);
        RefreshToken refreshToken = new RefreshTokenFactory().build(user);
        accessTokenTokenService.save(accessToken);
        refreshTokenTokenService.save(refreshToken);

        final LogInResponseDTO logInResponseDTO = LogInResponseDTO
                .builder()
                .userEntityDTO(userEntityService.mapper(user))
                .accessToken(accessToken.getToken())
                .refreshToken(refreshToken.getToken())
                .build();

        log.info("User logged in successfully: {}", logInDTO.getEmail());
        return new CustomerResponse<>(logInResponseDTO, HttpStatus.OK);*/
        return null;
    }

    @Override
    public CustomerResponse<String> validateToken(String token) {
        log.debug("Validating token: {}", token);

        final AccessToken accessToken = accessTokenTokenService.findByToken(token);
        final boolean isValid = jwtTokenProvider.validateToken(token);

        if (!isValid) {
            log.warn("Invalid JWT token: {}", token);
            return new CustomerResponse<>("invalid Jwt token", HttpStatus.BAD_REQUEST);
        }

        if (accessToken.isExpired() || accessToken.isRevoked()) {
            log.warn("Expired or revoked JWT token: {}", token);
            return new CustomerResponse<>("expired Jwt token", HttpStatus.BAD_REQUEST);
        }

        log.info("JWT token is valid: {}", token);
        return new CustomerResponse<>("Valid Jwt token", HttpStatus.OK);
    }

    @Override
    public String confirmAccount(String confirmationToken) {
        log.debug("Activating account with token: {}", confirmationToken);

        ConfirmationToken existedConfirmationToken = confirmationTokenTokenService.findByToken(confirmationToken);

        if (existedConfirmationToken.isExpired() || existedConfirmationToken.isRevoked()) {
            log.warn("Invalid or expired confirmation token: {}", confirmationToken);
            throw new InvalidTokenException("Invalid Token.");
        }

        UserEntity existedUser = existedConfirmationToken.getUserEntity();

        if (existedUser.isEnabled()) {
            log.warn("Account already activated for token: {}", confirmationToken);
            throw new InvalidTokenException("Already activated");
        }

        existedUser.setEnabled(true);
        userEntityService.saveAndFlush(existedUser);

        log.info("Account activated successfully for token: {}", confirmationToken);
        return ThymeleafUtil.processEmailTemplate("confirmation-page", Collections.emptyMap());
    }
}
