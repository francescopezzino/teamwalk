package com.hugecorp.teamwalk.service;

import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.model.TeamDTO;

import java.util.Optional;

public interface TeamService {

    Optional<TeamDTO> createTeamWithEmployees(TeamDTO request);

    Optional<TeamDTO> removeStepCounter(Long teamId);

    Optional<TeamDTO> findTeamById(Long id);

}
