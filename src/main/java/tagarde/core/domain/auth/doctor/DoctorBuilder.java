package tagarde.core.domain.auth.doctor;

import tagarde.core.domain.role.Role;
import tagarde.core.domain.speciality.Speciality;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class DoctorBuilder {
    private UUID id;
    private String firsName;
    private String lastName;
    private String email;
    private String password;
    private boolean isEnabled;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Role role;
    private String phoneNumber;
    private String address;
    private Speciality speciality;
    private LocalDate dateOfBirth;
    private String codeCNOM;
    private String codeCNAM;
    public DoctorBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public DoctorBuilder firstName(String firstName) {
        this.firsName = firstName;
        return this;
    }

    public DoctorBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public DoctorBuilder email(String email) {
        this.email = email;
        return this;
    }
    public DoctorBuilder password(String password) {
        this.password = password;
        return this;
    }

    public DoctorBuilder isEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }
    public DoctorBuilder createAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }
    public DoctorBuilder updateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }
    public DoctorBuilder role(Role role) {
        this.role = role;
        return this;
    }
    public DoctorBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public DoctorBuilder address(String address) {
        this.address = address;
        return this;
    }
    public DoctorBuilder speciality(Speciality speciality) {
        this.speciality = speciality;
        return this;
    }
    public DoctorBuilder dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public DoctorBuilder codeCNOM(String codeCNOM) {
        this.codeCNOM = codeCNOM;
        return this;
    }
    public DoctorBuilder codeCNAM(String codeCNAM) {
        this.codeCNAM = codeCNAM;
        return this;
    }
    public Doctor build() {
        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setFirstName(firsName);
        doctor.setLastName(lastName);
        doctor.setEmail(email);
        doctor.setPassword(password);
        doctor.setCreatedAt(createAt);
        doctor.setUpdatedAt(updateAt);
        doctor.setRole(role);
        doctor.setPhoneNumber(phoneNumber);
        doctor.setDateOfBirth(dateOfBirth);
        doctor.setAddress(address);
        doctor.setSpeciality(speciality);
        doctor.setCodeCNOM(codeCNOM);
        doctor.setCodeCNAM(codeCNAM);
        return doctor;

    }

}
