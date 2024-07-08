package tagarde.core.domain.department;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.hospitalDepartment.HospitalDepartment;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
    @SequenceGenerator(
        name = "department_sequence",
        sequenceName = "department_sequence",
        allocationSize = 1
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<HospitalDepartment> hospitalDepartments;
}