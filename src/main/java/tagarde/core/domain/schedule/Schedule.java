package tagarde.core.domain.schedule;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.doctorSchedule.DoctorSchedule;
import tagarde.core.domain.hospitalDepartment.HospitalDepartment;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {

    @SequenceGenerator(
            name = "schedule_sequence",
            sequenceName = "schedule_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_sequence")
    private Long id;
    private LocalDate month;

    @ManyToOne
    @JoinColumn(name = "hospital_department_id")
    private HospitalDepartment hospitalDepartment;

    @OneToMany(mappedBy = "schedule")
    private List<DoctorSchedule> doctorSchedules;
}