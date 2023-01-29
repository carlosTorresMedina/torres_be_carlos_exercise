package com.ecore.roles.service.impl;

import static java.util.Optional.ofNullable;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.ecore.roles.client.model.Team;
import com.ecore.roles.exception.InvalidArgumentException;
import com.ecore.roles.exception.InvalidLogicException;
import com.ecore.roles.exception.ResourceExistsException;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Membership;
import com.ecore.roles.model.Role;
import com.ecore.roles.repository.MembershipRepository;
import com.ecore.roles.repository.RoleRepository;
import com.ecore.roles.service.MembershipService;
import com.ecore.roles.service.TeamService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final RoleRepository roleRepository;
    private final TeamService teamService;

    @Override
    public Membership assignRoleToMembership(@NonNull Membership membership) {

        final UUID roleId = ofNullable(membership.getRole()).map(Role::getId)
                .orElseThrow(() -> new InvalidArgumentException(Role.class));

        final UUID teamId = ofNullable(membership.getTeamId())
                .orElseThrow(() -> new InvalidArgumentException(UUID.class));

        final UUID userId = ofNullable(membership.getUserId())
                .orElseThrow(() -> new InvalidArgumentException(UUID.class));

        if (membershipRepository.findByUserIdAndTeamIdAndRoleId(userId, teamId, roleId)
                .isPresent()) {
            throw new ResourceExistsException(Membership.class);
        }

        roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, roleId));

        final Team team = ofNullable(teamService.getTeam(teamId))
                .orElseThrow(() -> new ResourceNotFoundException(Team.class, teamId));

        if (!team.getTeamMemberIds().stream().anyMatch(id -> id.equals(userId))
                && !team.getTeamLeadId().equals(userId)) {
            throw new InvalidLogicException(Membership.class,
                    "The provided user doesn't belong to the provided team.");
        }

        return membershipRepository.save(membership);
    }

    @Override
    public List<Membership> getMemberships(@NonNull UUID roleId) {
        return membershipRepository.findByRoleId(roleId);
    }
}
