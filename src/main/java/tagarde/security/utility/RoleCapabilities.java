package tagarde.security.utility;

import tagarde.config.AuthenticationRoles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoleCapabilities {
    private static final Map<String, Set<String>> roleCapabilities = new HashMap<>();

    static {
        roleCapabilities.put(AuthenticationRoles.ROLE_ADMIN, Set.of(
                AuthenticationRoles.ROLE_ADMIN,
                AuthenticationRoles.ROLE_GENERAL_MANAGER,
                AuthenticationRoles.ROLE_HOSPITAL_OWNER,
                AuthenticationRoles.ROLE_DOCTOR
        ));
        roleCapabilities.put(AuthenticationRoles.ROLE_HOSPITAL_OWNER, Set.of(
                AuthenticationRoles.ROLE_GENERAL_MANAGER
        ));
        roleCapabilities.put(AuthenticationRoles.ROLE_GENERAL_MANAGER, Set.of(
                AuthenticationRoles.ROLE_DOCTOR
        ));
    }

    public static boolean canCreateRole(String currentRole, String targetRole) {
        return roleCapabilities.getOrDefault(currentRole, Collections.emptySet()).contains(targetRole);
    }
}
