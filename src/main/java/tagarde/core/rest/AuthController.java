package tagarde.core.rest;

import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import tagarde.core.domain.auth.LogInDTO;
import tagarde.core.domain.auth.LogInResponseDTO;
import tagarde.core.domain.auth.RegisterDTO;
import tagarde.core.domain.auth.RegisterResponseDTO;
import tagarde.core.service.auth.AuthService;
import tagarde.config.APIRouters;
import tagarde.core.utility.CustomerResponse;


@RestController
@RequestMapping(APIRouters.AUTH_ROUTER)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public CustomerResponse<RegisterResponseDTO> register(@NotNull @Valid @RequestBody final RegisterDTO registerDTO) {
        return authService.register(registerDTO);
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
