package tagarde.core.domain.auth.register;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterDoctorDTO extends RegisterUserDTO {

    private String speciality;
    private LocalDate dateOfBirth;
    private String codeCNOM;
    private String codeCNAM;

}
