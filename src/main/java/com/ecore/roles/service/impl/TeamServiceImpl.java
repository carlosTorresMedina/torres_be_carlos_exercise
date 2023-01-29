package com.ecore.roles.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.ecore.roles.client.TeamRestClient;
import com.ecore.roles.client.model.Team;
import com.ecore.roles.service.TeamService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRestClient teamRestClient;

    public Team getTeam(UUID teamId) {
        return teamRestClient.getTeam(teamId).getBody();
    }

    public List<Team> getTeams() {
        return teamRestClient.getTeams().getBody();
    }
}
