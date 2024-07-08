package tagarde.core.domain.auth.hospitalOwner;

import tagarde.core.domain.role.Role;

import java.util.UUID;

public class HospitalOwnerBuilder {

    private UUID id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isEnabled;
    private Role role;

    public HospitalOwnerBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public HospitalOwnerBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public HospitalOwnerBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public HospitalOwnerBuilder address(String address) {
        this.address = address;
        return this;
    }

    public HospitalOwnerBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public HospitalOwnerBuilder email(String email) {
        this.email = email;
        return this;
    }

    public HospitalOwnerBuilder password(String password) {
        this.password = password;
        return this;
    }

    public HospitalOwnerBuilder isEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public HospitalOwnerBuilder role(Role role) {
        this.role = role;
        return this;
    }

    public HospitalOwner build() {
        HospitalOwner hospitalOwner = new HospitalOwner();
        hospitalOwner.setId(id);
        hospitalOwner.setFirstName(firstName);
        hospitalOwner.setLastName(lastName);
        hospitalOwner.setAddress(address);
        hospitalOwner.setPhoneNumber(phoneNumber);
        hospitalOwner.setEmail(email);
        hospitalOwner.setPassword(password);
        hospitalOwner.setEnabled(isEnabled);
        hospitalOwner.setRole(role);
        return hospitalOwner;
    }
}
