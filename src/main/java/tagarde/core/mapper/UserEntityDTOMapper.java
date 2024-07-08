package tagarde.core.mapper;


import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import tagarde.core.domain.auth.user.UserEntity;
import tagarde.core.domain.auth.user.UserEntityDTO;

import java.util.function.Function;

@Service
public class UserEntityDTOMapper implements Function<UserEntity, UserEntityDTO> {
    @Override
    public UserEntityDTO apply(@NotNull UserEntity userEntity) {
        return new UserEntityDTO(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getRole(),
                userEntity.isEnabled()
        );
    }
}
