package tagarde.core.domain.speciality;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.auth.doctor.Doctor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "specialities")
public class Speciality {

    @SequenceGenerator(
            name = "specialty_sequence",
            sequenceName = "specialty_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "specialty_sequence")
    private long id;

    @Column(name = "speciality_name", nullable = false, unique = true)
    private String specialityName;

    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
    private List<Doctor> doctors;
}
