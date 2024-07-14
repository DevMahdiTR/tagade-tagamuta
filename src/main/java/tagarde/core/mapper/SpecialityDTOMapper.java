package tagarde.core.mapper;

import org.springframework.stereotype.Service;
import tagarde.core.domain.speciality.Speciality;
import tagarde.core.domain.speciality.SpecialityDTO;

import java.util.function.Function;

@Service
public class SpecialityDTOMapper implements Function<Speciality, SpecialityDTO> {
    @Override
    public SpecialityDTO apply(Speciality speciality) {
        return new SpecialityDTO(
                speciality.getId(),
                speciality.getSpecialityName()
        );
    }
}
