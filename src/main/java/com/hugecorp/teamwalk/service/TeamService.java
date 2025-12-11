package com.hugecorp.teamwalk.service;

import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.model.TeamDTO;

import java.util.Optional;

public interface TeamService {

    Team createTeamWithEmployees(TeamDTO request);

    Optional<Team> removeStepCounter(Long teamId);

    Optional<Team> findTeamById(Long id);

}
