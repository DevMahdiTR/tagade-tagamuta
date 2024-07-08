package tagarde.core.domain.auth.departmentManager;

import tagarde.core.domain.role.Role;

import java.util.UUID;

public class DepartmentManagerBuilder {

    private UUID id;
    private String email;
    private String password;
    private boolean isEnabled;
    private Role role;
    public DepartmentManagerBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public DepartmentManagerBuilder email(String email) {
        this.email = email;
        return this;
    }

    public DepartmentManagerBuilder password(String password) {
        this.password = password;
        return this;
    }

    public DepartmentManagerBuilder isEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public DepartmentManagerBuilder role(Role role) {
        this.role = role;
        return this;
    }


    public DepartmentManager build() {
        DepartmentManager departmentManager = new DepartmentManager();
        departmentManager.setId(id);
        departmentManager.setEmail(email);
        departmentManager.setPassword(password);
        departmentManager.setEnabled(isEnabled);
        departmentManager.setRole(role);
        return departmentManager;
    }

}
