package tagarde.core.domain.doctorSchedule;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.schedule.Schedule;
import tagarde.core.domain.auth.doctor.Doctor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "doctor_schedules")
public class DoctorSchedule {
    @SequenceGenerator(
            name = "doctor_schedule_sequence",
            sequenceName = "doctor_schedule_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_schedule_sequence")
    private Long id;

    @Column(nullable = false)
    private LocalDate workDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


}