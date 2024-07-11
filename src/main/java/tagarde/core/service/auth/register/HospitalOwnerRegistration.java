package tagarde.core.service.auth.register;

import org.springframework.stereotype.Service;
import tagarde.config.AuthenticationRoles;
import tagarde.core.domain.auth.register.RegisterUserDTO;
import tagarde.core.domain.auth.hospitalOwner.HospitalOwner;
@Service
public class HospitalOwnerRegistration extends UserRegistration<HospitalOwner> {

    @Override
    protected HospitalOwner createUser(RegisterUserDTO registerUserDTO) {
        return HospitalOwner.builder()
                .firstName(registerUserDTO.getFirstName())
                .lastName(registerUserDTO.getLastName())
                .address(registerUserDTO.getAddress())
                .phoneNumber(registerUserDTO.getPhoneNumber())
                .email(registerUserDTO.getEmail())
                .build();
    }

    @Override
    protected String getRole() {
        return AuthenticationRoles.ROLE_HOSPITAL_OWNER;
    }
}