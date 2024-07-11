package tagarde.core.domain.auth.doctor;

import tagarde.core.domain.role.Role;

import java.time.LocalDate;
import java.util.UUID;

public class DoctorBuilder {
    private UUID id;
    private String firsName;
    private String lastName;
    private String email;
    private String password;
    private boolean isEnabled;
    private Role role;
    private String phoneNumber;
    private String address;
    private String speciality;
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
    public DoctorBuilder speciality(String speciality) {
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
        doctor.setRole(role);
        doctor.setPhoneNumber(phoneNumber);
        doctor.setAddress(address);
        doctor.setSpeciality(speciality);
        doctor.setDateOfBirth(dateOfBirth);
        doctor.setCodeCNOM(codeCNOM);
        doctor.setCodeCNAM(codeCNAM);
        return doctor;

    }

}
