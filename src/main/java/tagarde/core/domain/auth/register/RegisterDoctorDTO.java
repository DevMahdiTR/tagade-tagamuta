package tagarde.core.domain.auth.register;


import lombok.Getter;
import lombok.Setter;
import tagarde.core.domain.speciality.Speciality;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterDoctorDTO extends RegisterUserDTO {

    private Speciality speciality;
    private LocalDate dateOfBirth;
    private String codeCNOM;
    private String codeCNAM;

}
