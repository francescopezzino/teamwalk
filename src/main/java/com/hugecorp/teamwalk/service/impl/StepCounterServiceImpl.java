package com.hugecorp.teamwalk.service.impl;

import com.hugecorp.teamwalk.domain.StepCounter;
import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.enums.State;
import com.hugecorp.teamwalk.mapper.StepCounterMapper;
import com.hugecorp.teamwalk.model.StepCounterDTO;
import com.hugecorp.teamwalk.repos.StepCounterRepository;
import com.hugecorp.teamwalk.repos.TeamRepository;
import com.hugecorp.teamwalk.service.StepCounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.hugecorp.teamwalk.enums.State.ENABLED;

@Service
@RequiredArgsConstructor
public class StepCounterServiceImpl implements StepCounterService {

    private final TeamRepository teamRepository;
    private final StepCounterMapper stepCounterMapper;
    private final StepCounterRepository stepCounterRepository;

    @Override
    @Transactional
    public  Optional<StepCounterDTO> addTeamStepCounter(StepCounterDTO stepCounterDto) {
        Optional<Team> teamOptional = teamRepository.findById(stepCounterDto.teamId());
        StepCounter stepCounter = null;
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            stepCounter = stepCounterMapper.toStepCounterEntity(stepCounterDto);
            stepCounter.setTeam(team);
            stepCounter.setState(ENABLED);
            stepCounterRepository.save(stepCounter);

            team.setStepcounter(stepCounter);
            teamRepository.save(team);
            stepCounter.setTeam(team);
        }
        return Optional.ofNullable(stepCounter).map(stepCounterMapper::toStepCounterDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StepCounterDTO> getStepCounterById(Long id) {
        Optional<StepCounter> stepCounterOptional = stepCounterRepository.findById(id);
        return Optional.ofNullable(stepCounterOptional.get()).map(stepCounterMapper::toStepCounterDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<StepCounterDTO>> getAllTeamScoreDesc() {
        List<StepCounter> stepCounters = stepCounterRepository.findAllByOrderByStepsDesc();

        List<StepCounter> stepCountersEnabled = stepCounters.stream().filter(stepCounter -> stepCounter.getState() == ENABLED).toList();

        List<StepCounterDTO> stepCounterDtos  = stepCounterMapper.toStepCounterDtos(stepCountersEnabled);

        return Optional.ofNullable(stepCounterDtos);
    }

}
