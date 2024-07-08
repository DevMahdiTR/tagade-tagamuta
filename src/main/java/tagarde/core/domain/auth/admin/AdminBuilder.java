package tagarde.core.domain.auth.admin;

import tagarde.core.domain.role.Role;

import java.util.UUID;

public class AdminBuilder {
    private UUID id;
    private String email;
    private String password;
    private boolean isEnabled;
    private Role role;
    public AdminBuilder id(UUID id) {
        this.id = id;
        return this;
    };
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
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setEnabled(isEnabled);
        admin.setRole(role);
        return admin;
    }
}
