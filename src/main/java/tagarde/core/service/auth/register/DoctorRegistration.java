package tagarde.core.service.auth.register;

import org.springframework.stereotype.Service;
import tagarde.config.AuthenticationRoles;
import tagarde.core.domain.auth.register.RegisterDoctorDTO;
import tagarde.core.domain.auth.doctor.Doctor;
import tagarde.core.domain.auth.register.RegisterUserDTO;

@Service
public class DoctorRegistration extends UserRegistration<Doctor>{

    @Override
    protected Doctor createUser(RegisterUserDTO registerUserDTO) {

        RegisterDoctorDTO registerDoctorDTO = (RegisterDoctorDTO) registerUserDTO;

        return Doctor.builder()
                .firstName(registerDoctorDTO.getFirstName())
                .lastName(registerDoctorDTO.getLastName())
                .address(registerDoctorDTO.getAddress())
                .phoneNumber(registerDoctorDTO.getPhoneNumber())
                .email(registerDoctorDTO.getEmail())
                .dateOfBirth(registerDoctorDTO.getDateOfBirth())
                .speciality(registerDoctorDTO.getSpeciality())
                .codeCNAM(registerDoctorDTO.getCodeCNAM())
                .codeCNOM(registerDoctorDTO.getCodeCNAM())
                .build();
    }

    @Override
    protected String getRole() {
        return AuthenticationRoles.ROLE_DOCTOR;
    }

}
