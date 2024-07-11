package tagarde.core.rest;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tagarde.core.domain.auth.login.LogInDTO;
import tagarde.core.domain.auth.login.LogInResponseDTO;
import tagarde.core.domain.auth.register.RegisterDoctorDTO;
import tagarde.core.domain.auth.register.RegisterUserDTO;
import tagarde.core.service.auth.AuthService;
import tagarde.config.APIRouters;
import tagarde.core.utility.CustomerResponse;


@RestController
@RequestMapping(APIRouters.AUTH_ROUTER)
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public CustomerResponse<String> register(Authentication authentication, @RequestParam String role, @RequestBody RegisterDoctorDTO registerUserDTO) {
        return authService.register(authentication,role, registerUserDTO);
    }

    @PostMapping("/login")
    public CustomerResponse<LogInResponseDTO> logIn(@NotNull @Valid @RequestBody final LogInDTO logInDTO) {
        return authService.logIn(logInDTO);
    }

    @GetMapping("/validate/{token}")
    public CustomerResponse<String> validateToken(@PathVariable("token") @NotNull final String token){
        return authService.validateToken(token);
    }

    @GetMapping("/confirm")
    public String activateAccount(@RequestParam("token")final String confirmationToken) {
        return authService.confirmAccount(confirmationToken);
    }


}
