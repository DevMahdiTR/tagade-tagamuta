package tagarde.core.service.user;



import tagarde.core.domain.auth.user.UserEntityDTO;
import tagarde.core.domain.auth.user.UserEntity;

import java.util.UUID;

public interface UserEntityService {


    UserEntityDTO mapper(final UserEntity userEntity);

    UserEntity save(final UserEntity userEntity);
    UserEntity saveAndFlush(final UserEntity userEntity);

    UserEntity getUserByUUID(final UUID userId);
    UserEntity getUserByEmail(final String email);

    Boolean isEmailRegistered(final String email);


}
