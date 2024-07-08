package tagarde.core.service.role;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import tagarde.core.exceptions.custom.DatabaseException;
import tagarde.core.exceptions.custom.ResourceNotFoundException;
import tagarde.core.domain.role.Role;
import tagarde.core.repository.RoleRepository;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role fetchRoleByName(String name) {
        log.debug("Fetching role by name: {}", name);
        return roleRepository.fetchRoleByName(name).orElseThrow(
                () -> new ResourceNotFoundException("The Role with name %s could not be found.".formatted(name))
        );
    }

    @Transactional
    @Override
    public Role deleteRoleById(Long id) {
        log.debug("Deleting role by ID: {}", id);
        Role existingRole = getRoleById(id);
        roleRepository.removeRoleById(id);
        log.info("Role deleted successfully: {}", existingRole);
        return existingRole;
    }

    @Override
    public Role updateRoleById(Long id, @NotNull Role role) {
        log.debug("Updating role by ID: {} with data: {}", id, role);
        Role existingRole = getRoleById(id);
        role.setId(existingRole.getId());
        Role updatedRole = this.save(role);
        log.info("Role updated successfully: {}", updatedRole);
        return updatedRole;
    }

    @Override
    public Role save(Role role) {
        try {
            log.debug("Saving role: {}", role);
            Role savedRole = roleRepository.save(role);
            log.info("Role saved successfully: {}", savedRole);
            return savedRole;
        } catch (Exception e) {
            log.error("Error saving role: {}", role, e);
            throw new DatabaseException("Error executing database query.");
        }
    }

    @Override
    public Role getRoleById(Long id) {
        log.debug("Fetching role by ID: {}", id);
        return roleRepository.fetchRoleById(id).orElseThrow(
                () -> new ResourceNotFoundException("The Role with ID : '%d' could not be found".formatted(id))
        );
    }
}
