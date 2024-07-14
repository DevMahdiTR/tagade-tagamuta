package tagarde.core.mapper;

import org.springframework.stereotype.Service;
import tagarde.core.domain.auth.hospitalOwner.HospitalOwner;
import tagarde.core.domain.auth.hospitalOwner.HospitalOwnerDTO;

import java.util.function.Function;

@Service
public class HospitalOwnerDTOMapper implements Function<HospitalOwner, HospitalOwnerDTO> {
    @Override
    public HospitalOwnerDTO apply(HospitalOwner hospitalOwner) {
        return new HospitalOwnerDTO(
                hospitalOwner.getId(),
                hospitalOwner.getFirstName(),
                hospitalOwner.getLastName(),
                hospitalOwner.getAddress(),
                hospitalOwner.getPhoneNumber(),
                hospitalOwner.getEmail(),
                hospitalOwner.getCreatedAt(),
                hospitalOwner.getUpdatedAt(),
                hospitalOwner.getRole(),
                hospitalOwner.isEnabled(),
                hospitalOwner.getHospital() == null ? -1 : hospitalOwner.getHospital().getId()
        );
    }
}
