package com.ecore.roles.service;

import java.util.List;
import java.util.UUID;
import com.ecore.roles.client.model.Team;

public interface TeamService {

    Team getTeam(UUID id);

    List<Team> getTeams();
}
