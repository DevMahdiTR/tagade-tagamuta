package tagarde.core.domain.auth.user;


import tagarde.core.domain.role.Role;

import java.util.UUID;

public record UserEntityDTO(
        UUID id,
        String email,
        Role role,
        boolean isEnabled
) {
}
