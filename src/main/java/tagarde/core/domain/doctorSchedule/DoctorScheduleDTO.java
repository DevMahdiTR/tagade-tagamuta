package tagarde.core.domain.doctorSchedule;

import java.time.LocalDate;
import java.util.UUID;

public record DoctorScheduleDTO (
        long id,
        LocalDate workday
) {
}
