package com.hugecorp.teamwalk.service;

import com.hugecorp.teamwalk.domain.StepCounter;
import com.hugecorp.teamwalk.model.StepCounterDTO;

import java.util.List;
import java.util.Optional;

public interface StepCounterService {

    StepCounter addTeamStepCounter(StepCounterDTO stepCounterDto);

    void deleteStepCounter(StepCounter stepCounter);

    Optional<StepCounter> findStepCounterById(Long id);

    List<StepCounter> findAllTeamScoreDesc();
}
