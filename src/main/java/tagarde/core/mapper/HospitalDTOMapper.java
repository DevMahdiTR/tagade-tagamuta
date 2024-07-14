package tagarde.core.mapper;

import org.springframework.stereotype.Service;
import tagarde.core.domain.hospital.Hospital;
import tagarde.core.domain.hospital.HospitalDTO;

import java.util.function.Function;
@Service
public class HospitalDTOMapper implements Function<Hospital, HospitalDTO> {
    @Override
    public HospitalDTO apply(Hospital hospital) {
        return new HospitalDTO(
                hospital.getId(),
                hospital.getName(),
                hospital.getAddress(),
                hospital.getPhone(),
                hospital.getEmail(),
                hospital.getHospitalOwner() == null ? null : new UserEntityDTOMapper().apply(hospital.getHospitalOwner()),
                hospital.getGeneralManager() == null ? null : new UserEntityDTOMapper().apply(hospital.getGeneralManager())
        );
    }
}
