package com.ecore.roles.service.impl;

import static java.util.Optional.ofNullable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.ecore.roles.client.model.Team;
import com.ecore.roles.client.model.User;
import com.ecore.roles.exception.ResourceExistsException;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Role;
import com.ecore.roles.repository.MembershipRepository;
import com.ecore.roles.repository.RoleRepository;
import com.ecore.roles.service.RoleService;
import com.ecore.roles.service.TeamService;
import com.ecore.roles.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MembershipRepository membershipRepository;
    private final TeamService teamService;
    private final UserService userService;

    @Override
    public Role createRole(@NonNull Role role) {
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new ResourceExistsException(Role.class);
        }
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(@NonNull UUID roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, roleId));
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getRolesByUserIdAndTeamId(@NonNull UUID userId, @NonNull UUID teamId) {

        final Team team = ofNullable(teamService.getTeam(teamId))
                .orElseThrow(() -> new ResourceNotFoundException(Team.class, teamId));

        final User user = ofNullable(userService.getUser(userId))
                .orElseThrow(() -> new ResourceNotFoundException(User.class, userId));

        return membershipRepository.findByUserIdAndTeamId(user.getId(), team.getId()).stream()
                .map(membership -> membership.getRole()).collect(Collectors.toList());

    }

}
