package com.ecore.roles.service;

import java.util.List;
import java.util.UUID;
import com.ecore.roles.model.Role;
import lombok.NonNull;

public interface RoleService {

    Role createRole(Role role);

    Role getRole(UUID id);

    List<Role> getRoles();

    List<Role> getRolesByUserIdAndTeamId(@NonNull UUID userId, @NonNull UUID teamId);

}
