package com.hugecorp.teamwalk.service.impl;

import com.hugecorp.teamwalk.domain.StepCounter;
import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.mapper.StepCounterMapper;
import com.hugecorp.teamwalk.model.StepCounterDTO;
import com.hugecorp.teamwalk.repos.StepCounterRepository;
import com.hugecorp.teamwalk.repos.TeamRepository;
import com.hugecorp.teamwalk.service.StepCounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StepCounterServiceImpl implements StepCounterService {

    private final TeamRepository teamRepository;
    private final StepCounterMapper stepCounterMapper;
    private final StepCounterRepository stepCounterRepository;

    @Override
    @Transactional
    public StepCounter addTeamStepCounter(StepCounterDTO stepCounterDto) {
        Optional<Team> teamOptional = teamRepository.findById(stepCounterDto.teamId());
        StepCounter stepCounter = null;
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            stepCounter = stepCounterMapper.toStepCounterEntity(stepCounterDto);
            stepCounter.setTeam(team);
            stepCounter = stepCounterRepository.save(stepCounter);

            team.setStepcounter(stepCounter);
            teamRepository.save(team);
            stepCounter.setTeam(team);
        }
        return stepCounter;
    }

    @Override
    @Transactional
    public void deleteStepCounter(StepCounter stepCounter)  {
        stepCounterRepository.delete(stepCounter);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StepCounter> findStepCounterById(Long id) {
        Optional<StepCounter> stepCounterOptional = stepCounterRepository.findById(id);
        if (stepCounterOptional.isPresent()) {
            return stepCounterOptional;
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StepCounter> findAllTeamScoreDesc() {
        return stepCounterRepository.findAllByOrderByStepsDesc();
    }

}
