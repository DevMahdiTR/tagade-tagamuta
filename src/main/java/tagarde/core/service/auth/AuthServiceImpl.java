package tagarde.core.service.auth;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tagarde.config.AuthenticationRoles;
import tagarde.core.domain.auth.doctor.Doctor;
import tagarde.core.domain.auth.login.LogInDTO;
import tagarde.core.domain.auth.login.LogInResponseDTO;
import tagarde.core.domain.auth.register.RegisterDoctorDTO;
import tagarde.core.domain.auth.register.RegisterUserDTO;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.core.domain.token.access.AccessToken;
import tagarde.core.domain.token.access.AccessTokenFactory;
import tagarde.core.domain.token.confirmation.ConfirmationToken;
import tagarde.core.domain.token.refresh.RefreshToken;
import tagarde.core.domain.token.refresh.RefreshTokenFactory;
import tagarde.core.exceptions.custom.InvalidTokenException;
import tagarde.core.exceptions.custom.UnauthorizedActionException;
import tagarde.core.service.auth.register.AdminRegistration;
import tagarde.core.service.auth.register.DoctorRegistration;
import tagarde.core.service.auth.register.GeneralManagerRegistration;
import tagarde.core.service.auth.register.HospitalOwnerRegistration;
import tagarde.core.service.token.TokenService;
import tagarde.core.service.user.UserEntityService;
import tagarde.core.utility.CustomerResponse;
import tagarde.core.utility.ThymeleafUtil;
import tagarde.security.jwt.JwtTokenProvider;
import tagarde.security.utility.RoleCapabilities;

import java.util.Collections;


@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserEntityService userEntityService;
    private final JwtTokenProvider jwtTokenProvider;

    private final TokenService<AccessToken> accessTokenTokenService;
    private final TokenService<RefreshToken> refreshTokenTokenService;
    private final TokenService<ConfirmationToken> confirmationTokenTokenService;

    private final AdminRegistration adminRegistration;
    private final GeneralManagerRegistration generalManagerRegistration;
    private final HospitalOwnerRegistration hospitalOwnerRegistration;
    private final DoctorRegistration doctorRegistration;


    @Override
    public CustomerResponse<String> register(Authentication authentication,@NotNull String role, @NotNull RegisterUserDTO registerUserDTO) {

//        if(authentication == null){
//            throw new UnauthorizedActionException("Unauthorized action");
//        }
//
//        String currentRole = authentication.getAuthorities().iterator().next().getAuthority();
//
//        if(!RoleCapabilities.canCreateRole(currentRole, role)){
//            throw new UnauthorizedActionException("Unauthorized action");
//        }

        return switch (role) {
            case AuthenticationRoles.ROLE_ADMIN -> adminRegistration.register(registerUserDTO);
            case AuthenticationRoles.ROLE_GENERAL_MANAGER -> generalManagerRegistration.register(registerUserDTO);
            case AuthenticationRoles.ROLE_HOSPITAL_OWNER -> hospitalOwnerRegistration.register(registerUserDTO);
            case AuthenticationRoles.ROLE_DOCTOR -> doctorRegistration.register(registerUserDTO);
            default -> new CustomerResponse<>("Invalid role", HttpStatus.BAD_REQUEST);
        };
    }

    @Override
    public CustomerResponse<LogInResponseDTO> logIn(@NotNull LogInDTO logInDTO) {
        log.debug("Attempting to log in user with email: {}", logInDTO.getEmail());

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
        return new CustomerResponse<>(logInResponseDTO, HttpStatus.OK);
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
