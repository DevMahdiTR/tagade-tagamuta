package tagarde.core.domain.auth.admin;

import tagarde.core.domain.role.Role;

import java.util.UUID;

public class AdminBuilder {
    private UUID id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isEnabled;
    private Role role;

    public AdminBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public AdminBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AdminBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AdminBuilder address(String address) {
        this.address = address;
        return this;
    }

    public AdminBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AdminBuilder email(String email) {
        this.email = email;
        return this;
    }
    public AdminBuilder password(String password) {
        this.password = password;
        return this;
    }
    public AdminBuilder isEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }
    public AdminBuilder role(Role role) {
        this.role = role;
        return this;
    }
    public Admin build() {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setAddress(address);
        admin.setPhoneNumber(phoneNumber);
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setEnabled(isEnabled);
        admin.setRole(role);
        return admin;
    }
}
