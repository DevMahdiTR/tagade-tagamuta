package tagarde.core.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tagarde.core.domain.auth.login.LogInDTO;
import tagarde.core.domain.auth.login.LogInResponseDTO;
import tagarde.core.domain.auth.register.RegisterDoctorDTO;
import tagarde.core.service.auth.AuthService;
import tagarde.config.APIRouters;
import tagarde.core.utility.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@Slf4j
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(APIRouters.AUTH_ROUTER)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Register a new user", description = "Registers a new user based on the role provided")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public CustomerResponse<String> register(
            Authentication authentication,
            @Parameter(description = "Role of the user to register", required = true) @RequestParam String role,
            @RequestBody RegisterDoctorDTO registerUserDTO) {
        return authService.register(authentication, role, registerUserDTO);
    }

    @Operation(summary = "User login", description = "Logs in a user and returns authentication details including access and refresh tokens.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully", content = @Content(schema = @Schema(implementation = LogInResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid login credentials"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/login")
    public CustomerResponse<LogInResponseDTO> logIn(
            @Parameter(description = "Login details", required = true) @NotNull @Valid @RequestBody final LogInDTO logInDTO) {
        return authService.logIn(logInDTO);
    }

    @Operation(summary = "Validate token", description = "Validates a given token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token is valid"),
            @ApiResponse(responseCode = "400", description = "Invalid token"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/validate/{token}")
    public CustomerResponse<String> validateToken(
            @Parameter(description = "Token to validate", required = true) @PathVariable("token") @NotNull final String token) {
        return authService.validateToken(token);
    }

    @Operation(summary = "Confirm account", description = "Confirms a user's account based on the confirmation token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account confirmed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid confirmation token"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/confirm")
    public String activateAccount(
            @Parameter(description = "Confirmation token", required = true) @RequestParam("token") final String confirmationToken) {
        return authService.confirmAccount(confirmationToken);
    }
}
