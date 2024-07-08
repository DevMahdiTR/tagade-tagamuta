package tagarde.core.domain.hospitalDepartment;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.department.Department;
import tagarde.core.domain.hospital.Hospital;
import tagarde.core.domain.schedule.Schedule;
import tagarde.core.domain.auth.doctor.Doctor;
import tagarde.core.domain.auth.departmentManager.DepartmentManager;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "hospital_departments")
public class HospitalDepartment {

    @SequenceGenerator(
        name = "hospital_department_sequence",
        sequenceName = "hospital_department_sequence",
        allocationSize = 1
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_department_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "hospitalDepartment", fetch = FetchType.EAGER)
    private DepartmentManager departmentManager;

    @OneToMany(mappedBy = "hospitalDepartment", fetch = FetchType.EAGER)
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "hospitalDepartment", fetch = FetchType.EAGER)
    private List<Schedule> schedules;
}
