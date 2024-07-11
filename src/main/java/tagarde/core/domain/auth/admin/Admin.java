package tagarde.core.domain.auth.admin;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import tagarde.core.domain.auth.user.UserEntity;


@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "admins")
@DiscriminatorValue("ADMIN")
public class Admin  extends UserEntity {


    public static AdminBuilder builder(){
        return new AdminBuilder();
    }
}
