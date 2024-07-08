package tagarde.core.domain.auth.departmentManager;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.hospitalDepartment.HospitalDepartment;
import tagarde.core.domain.auth.user.UserEntity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "department_managers")
@DiscriminatorValue("DEPARTMENT_MANAGER")
public class DepartmentManager extends UserEntity {


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_department_id")
    private HospitalDepartment hospitalDepartment;


    public static DepartmentManagerBuilder builder() {
        return new DepartmentManagerBuilder();
    }

}