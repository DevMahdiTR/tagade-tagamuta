package tagarde.core.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import tagarde.core.domain.auth.doctor.Doctor;
import tagarde.core.domain.auth.doctor.DoctorDTO;

import java.util.function.Function;

@Service
public class DoctorDTOMapper implements Function<Doctor, DoctorDTO> {
    @Override
    public DoctorDTO apply(@NotNull Doctor doctor) {
        return new DoctorDTO(
                doctor.getId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getAddress(),
                doctor.getPhoneNumber(),
                doctor.getEmail(),
                doctor.getCreatedAt(),
                doctor.getUpdatedAt(),
                doctor.getRole(),
                doctor.isEnabled(),
                doctor.getDateOfBirth(),
                doctor.getCodeCNOM(),
                doctor.getCodeCNAM(),
                doctor.getSpeciality() == null ? null : new SpecialityDTOMapper().apply(doctor.getSpeciality()),
                doctor.getHospitalDepartment() == null ? -1 : doctor.getHospitalDepartment().getId()
        );
    }
}
