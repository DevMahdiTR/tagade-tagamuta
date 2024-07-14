package tagarde.core.domain.auth.generalManager;

import tagarde.core.domain.role.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class GeneralManagerBuilder {

    private UUID id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isEnabled;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Role role;

    public GeneralManagerBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public GeneralManagerBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public GeneralManagerBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public GeneralManagerBuilder address(String address) {
        this.address = address;
        return this;
    }

    public GeneralManagerBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public GeneralManagerBuilder email(String email) {
        this.email = email;
        return this;
    }

    public GeneralManagerBuilder password(String password) {
        this.password = password;
        return this;
    }

    public GeneralManagerBuilder isEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public GeneralManagerBuilder createAt(LocalDateTime createAt) {
        this.createAt = createAt;
        return this;
    }

    public GeneralManagerBuilder updateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public GeneralManagerBuilder role(Role role) {
        this.role = role;
        return this;
    }

    public GeneralManager build(){
        GeneralManager generalManager = new GeneralManager();
        generalManager.setId(id);
        generalManager.setFirstName(firstName);
        generalManager.setLastName(lastName);
        generalManager.setAddress(address);
        generalManager.setPhoneNumber(phoneNumber);
        generalManager.setEmail(email);
        generalManager.setPassword(password);
        generalManager.setEnabled(isEnabled);
        generalManager.setCreatedAt(createAt);
        generalManager.setUpdatedAt(updateAt);
        generalManager.setRole(role);
        return generalManager;
    }
}
