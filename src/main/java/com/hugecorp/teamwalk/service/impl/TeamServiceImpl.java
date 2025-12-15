package com.hugecorp.teamwalk.service.impl;

import com.hugecorp.teamwalk.domain.Employee;
import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.mapper.TeamMapper;
import com.hugecorp.teamwalk.model.TeamDTO;
import com.hugecorp.teamwalk.repos.TeamRepository;
import com.hugecorp.teamwalk.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;

    @Transactional
    public Optional<TeamDTO> createTeamWithEmployees(TeamDTO teamDto) {
        Team team = teamMapper.toTeamEntity(teamDto);
        teamRepository.save(team); // Saving the team cascades the save to employees
        for (Employee employee : team.getEmployees()) {
            employee.setTeam(team);
        }
        return Optional.ofNullable(team).map(teamMapper::toTeamDto);
    }

    @Override
    @Transactional
    public Optional<TeamDTO> removeStepCounter(Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        Team team = null;
        if (teamOptional.isPresent()) {
            team = teamOptional.get();

            team.setStepcounter(null);
            teamRepository.save(team);
        }
        return Optional.ofNullable(team).map(teamMapper::toTeamDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeamDTO> findTeamById(Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        return Optional.ofNullable(teamOptional.get()).map(teamMapper::toTeamDto);
    }
}
