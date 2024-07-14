package tagarde.core.mapper;

import org.springframework.stereotype.Service;
import tagarde.core.domain.doctorSchedule.DoctorSchedule;
import tagarde.core.domain.doctorSchedule.DoctorScheduleDTO;

import java.util.function.Function;

@Service
public class DoctorScheduleDTOMapper implements Function<DoctorSchedule , DoctorScheduleDTO> {
    @Override
    public DoctorScheduleDTO apply(DoctorSchedule doctorSchedule) {
        return new DoctorScheduleDTO(
                doctorSchedule.getId(),
                doctorSchedule.getWorkDay()
        );
    }
}
