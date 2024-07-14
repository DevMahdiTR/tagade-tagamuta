package tagarde.core.domain.hospital;

import tagarde.core.domain.auth.user.UserDTO;

public record HospitalDTO (
        long id,
        String name,
        String address,
        String phone,
        String email,
        UserDTO hospitalOwner,
        UserDTO generalManager
){
}
