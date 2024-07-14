package tagarde.core.domain.auth.user;

import tagarde.core.domain.role.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserDTO {
    UUID getId();
    String getFirstName();
    String getLastName();
    String getAddress();
    String getPhoneNumber();
    String getEmail();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    Role getRole();
    boolean isEnabled();
}