package com.hugecorp.teamwalk.service.impl;

import com.hugecorp.teamwalk.domain.Employee;
import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.mapper.TeamMapper;
import com.hugecorp.teamwalk.model.TeamDTO;
import com.hugecorp.teamwalk.repos.StepCounterRepository;
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
    public Team createTeamWithEmployees(TeamDTO teamDto) {

        Team team = teamMapper.toTeamEntity(teamDto);
         team = teamRepository.save(team); // Saving the team cascades the save to employees
         for(Employee employee: team.getEmployees()) {
             employee.setTeam(team);
         }
        return team;
    }

    @Override
    @Transactional
    public Optional<Team> removeStepCounter(Long id) {
        Optional<Team> teamOptional =  teamRepository.findById(id);
        Team team = null;
        if (teamOptional.isPresent()) {
            team = teamOptional.get();

            team.setStepcounter(null);
            teamRepository.save(team);
        }
        return Optional.ofNullable(team);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Team> findTeamById(Long id) {

        Optional<Team> teamOptional =  teamRepository.findById(id);
        Team team = null;
        if (teamOptional.isPresent()) {
           return Optional.of(teamOptional.get());
        }
        return Optional.empty();
    }

}
