package tagarde.core.service.auth.register;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tagarde.config.APIRouters;
import tagarde.core.domain.auth.register.RegisterUserDTO;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.core.domain.token.confirmation.ConfirmationToken;
import tagarde.core.domain.token.confirmation.ConfirmationTokenFactory;
import tagarde.core.domain.token.refresh.RefreshToken;
import tagarde.core.domain.token.refresh.RefreshTokenFactory;
import tagarde.core.exceptions.custom.EmailRegisteredException;
import tagarde.core.service.email.EmailService;
import tagarde.core.service.role.RoleService;
import tagarde.core.service.token.TokenService;
import tagarde.core.service.user.UserEntityService;
import tagarde.core.utility.CustomerResponse;

import java.util.Map;

@Slf4j
@Service
public abstract class UserRegistration<T extends UserEntity> {

    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserEntityService userEntityService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private  TokenService<RefreshToken> refreshTokenTokenService;
    @Autowired
    private  TokenService<ConfirmationToken> confirmationTokenTokenService;
    @Autowired
    private  EmailService emailService;

    protected abstract T createUser(RegisterUserDTO registerUserDTO);
    protected abstract String getRole();

    public CustomerResponse<String> register(@NotNull RegisterUserDTO registerUserDTO) {
        log.debug("Attempting to register user with email: {}", registerUserDTO.getEmail());

        if (userEntityService.isEmailRegistered(registerUserDTO.getEmail())) {
            log.warn("Email already registered: {}", registerUserDTO.getEmail());
            throw new EmailRegisteredException("Email already in use.");
        }

        T newUser = createUser(registerUserDTO);
        newUser.setRole(roleService.fetchRoleByName(getRole()));
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        newUser.setEnabled(false);

        UserEntity savedUser = userEntityService.save(newUser);

        RefreshToken refreshToken = new RefreshTokenFactory().build(savedUser);
        ConfirmationToken confirmationToken = new ConfirmationTokenFactory().build(savedUser);
        confirmationTokenTokenService.saveAndFlush(confirmationToken);
        refreshTokenTokenService.saveAndFlush(refreshToken);

        String activationLink = APIRouters.getConfirmationURL(confirmationToken.getToken());

        log.debug("Sending activation email to: {}", registerUserDTO.getEmail());
        emailService.sendMail(
                registerUserDTO.getEmail(),
                "Activating your account.",
                "email-confirmation",
                Map.of(
                        "name", registerUserDTO.getEmail(),
                        "link", activationLink
                )
        );

        log.info("User registered successfully: {}", registerUserDTO.getEmail());
        return new CustomerResponse<>("User registered successfully", HttpStatus.CREATED);
    }
}