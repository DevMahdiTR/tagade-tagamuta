package tagarde.core.domain.auth.doctor;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.doctorSchedule.DoctorSchedule;
import tagarde.core.domain.hospitalDepartment.HospitalDepartment;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.core.domain.speciality.Speciality;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "doctors")
@DiscriminatorValue("DOCTOR")
public class Doctor extends UserEntity {

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "code_CNOM")
    private String codeCNOM;
    @Column(name = "code_CNAM")
    private String codeCNAM;

    public static DoctorBuilder builder(){
        return new DoctorBuilder();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_department_id")
    private HospitalDepartment hospitalDepartment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<DoctorSchedule> doctorSchedules;


}
