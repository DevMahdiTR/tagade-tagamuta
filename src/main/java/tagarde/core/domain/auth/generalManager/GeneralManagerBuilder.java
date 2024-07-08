package tagarde.core.domain.auth.generalManager;

import tagarde.core.domain.auth.departmentManager.DepartmentManager;
import tagarde.core.domain.role.Role;

import java.util.UUID;

public class GeneralManagerBuilder {

    private UUID id;
    private String email;
    private String password;
    private boolean isEnabled;
    private Role role;

    public GeneralManagerBuilder id(UUID id) {
        this.id = id;
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

    public GeneralManagerBuilder role(Role role) {
        this.role = role;
        return this;
    }

    public GeneralManager build(){
        GeneralManager generalManager = new GeneralManager();
        generalManager.setId(id);
        generalManager.setEmail(email);
        generalManager.setPassword(password);
        generalManager.setEnabled(isEnabled);
        generalManager.setRole(role);
        return generalManager;
    }
}
