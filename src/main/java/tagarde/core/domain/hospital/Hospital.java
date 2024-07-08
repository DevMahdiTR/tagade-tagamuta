package tagarde.core.domain.hospital;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.auth.generalManager.GeneralManager;
import tagarde.core.domain.auth.hospitalOwner.HospitalOwner;
import tagarde.core.domain.hospitalDepartment.HospitalDepartment;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "hospitals")
public class Hospital {

    @SequenceGenerator(
        name = "hospital_sequence",
        sequenceName = "hospital_sequence",
        allocationSize = 1
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_sequence")
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.EAGER)
    private List<HospitalDepartment> hospitalDepartments;

    @OneToOne(mappedBy = "hospital", fetch = FetchType.EAGER)
    private HospitalOwner hospitalOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "general_manager_id")
    private GeneralManager generalManager;
}