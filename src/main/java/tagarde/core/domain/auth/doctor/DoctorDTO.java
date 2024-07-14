package tagarde.core.domain.auth.doctor;

import tagarde.core.domain.auth.user.UserDTO;
import tagarde.core.domain.role.Role;
import tagarde.core.domain.speciality.SpecialityDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DoctorDTO(
        UUID id,
        String firstName,
        String lastName,
        String address,
        String phoneNumber,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Role role,
        boolean isEnabled,
        LocalDate dateOfBirth,
        String codeCNOM,
        String codeCNAM,
        SpecialityDTO specialityDTO,
        long HospitalDepartmentId

) implements UserDTO {
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
