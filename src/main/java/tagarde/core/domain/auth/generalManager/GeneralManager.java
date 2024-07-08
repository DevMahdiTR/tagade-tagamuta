package tagarde.core.domain.auth.generalManager;

import jakarta.persistence.*;
import lombok.*;
import tagarde.core.domain.hospital.Hospital;
import tagarde.core.domain.auth.user.UserEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "general_managers")
@DiscriminatorValue("GENERAL_MANAGER")
public class GeneralManager extends UserEntity {

    @OneToMany(mappedBy = "generalManager", fetch = FetchType.EAGER)
    private List<Hospital> hoiptals;
}
