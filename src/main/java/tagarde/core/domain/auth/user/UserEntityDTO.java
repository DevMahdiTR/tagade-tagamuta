package tagarde.core.domain.auth.user;


import tagarde.core.domain.role.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserEntityDTO(
        UUID id,
        String firstName,
        String lastName,
        String address,
        String phoneNumber,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Role role,
        boolean isEnabled
) implements UserDTO{
    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public Role getRole() {
        return this.role;
    }
}
