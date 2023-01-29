package com.ecore.roles.web.rest;

import static com.ecore.roles.web.dto.RoleDto.fromModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecore.roles.model.Role;
import com.ecore.roles.service.RoleService;
import com.ecore.roles.web.RoleApi;
import com.ecore.roles.web.dto.RoleDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles")
public class RoleRestController implements RoleApi {

    private final RoleService rolesService;

    @Override
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto role) {
        return ResponseEntity.status(201).body(fromModel(rolesService.createRole(role.toModel())));
    }

    @Override
    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<RoleDto>> getRoles() {

        List<Role> getRoles = rolesService.getRoles();

        List<RoleDto> roleDtoList = new ArrayList<>();

        for (Role role : getRoles) {
            RoleDto roleDto = fromModel(role);
            roleDtoList.add(roleDto);
        }

        return ResponseEntity.status(200).body(roleDtoList);
    }

    @Override
    @GetMapping(path = "/{roleId}", produces = {"application/json"})
    public ResponseEntity<RoleDto> getRole(@PathVariable UUID roleId) {
        return ResponseEntity.status(200).body(fromModel(rolesService.getRole(roleId)));
    }

    @Override
    @GetMapping(path = "/search", produces = {"application/json"})
    public ResponseEntity<List<RoleDto>> getRoleByUserIdAndTeamId(
            @RequestParam UUID userId,
            @RequestParam UUID teamId) {

        List<Role> getRoles = rolesService.getRolesByUserIdAndTeamId(userId, teamId);

        List<RoleDto> roleDtoList = new ArrayList<>();

        for (Role role : getRoles) {
            RoleDto roleDto = fromModel(role);
            roleDtoList.add(roleDto);
        }

        return ResponseEntity.status(200).body(roleDtoList);
    }

}
