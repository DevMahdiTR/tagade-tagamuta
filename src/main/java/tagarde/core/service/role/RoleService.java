package tagarde.core.service.role;


import tagarde.core.domain.role.Role;

public interface RoleService {

    Role fetchRoleByName(final String name);

    Role deleteRoleById(final Long id);

    Role updateRoleById(final Long id, final Role role);

    Role save(final Role role);

    Role getRoleById(final Long id);
}
