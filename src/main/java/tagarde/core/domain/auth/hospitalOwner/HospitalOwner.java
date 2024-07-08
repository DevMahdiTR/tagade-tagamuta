package tagarde.core.domain.auth.hospitalOwner;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.hospital.Hospital;
import tagarde.core.domain.auth.user.UserEntity;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hospital_owners")
@DiscriminatorValue("HOSPITAL_OWNER")
public class HospitalOwner extends UserEntity {

    @OneToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
