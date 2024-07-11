package tagarde.core.service.auth.register;


import org.springframework.stereotype.Service;
import tagarde.config.AuthenticationRoles;
import tagarde.core.domain.auth.register.RegisterUserDTO;
import tagarde.core.domain.auth.generalManager.GeneralManager;
@Service
public class GeneralManagerRegistration extends UserRegistration<GeneralManager> {

    @Override
    protected GeneralManager createUser(RegisterUserDTO registerUserDTO) {
        return GeneralManager.builder()
                .firstName(registerUserDTO.getFirstName())
                .lastName(registerUserDTO.getLastName())
                .address(registerUserDTO.getAddress())
                .phoneNumber(registerUserDTO.getPhoneNumber())
                .email(registerUserDTO.getEmail())
                .build();
    }

    @Override
    protected String getRole() {
        return AuthenticationRoles.ROLE_GENERAL_MANAGER;
    }
}
