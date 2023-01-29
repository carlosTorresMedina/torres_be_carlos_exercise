package com.ecore.roles.web;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import com.ecore.roles.web.dto.RoleDto;

public interface RoleApi {

    ResponseEntity<RoleDto> createRole(RoleDto role);

    ResponseEntity<List<RoleDto>> getRoles();

    ResponseEntity<RoleDto> getRole(UUID roleId);

    ResponseEntity<List<RoleDto>> getRoleByUserIdAndTeamId(UUID teamMemberId, UUID teamId);

}
