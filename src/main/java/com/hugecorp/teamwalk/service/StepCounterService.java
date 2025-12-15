package com.hugecorp.teamwalk.service;

import com.hugecorp.teamwalk.domain.StepCounter;
import com.hugecorp.teamwalk.model.StepCounterDTO;

import java.util.List;
import java.util.Optional;

public interface StepCounterService {

    Optional<StepCounterDTO> addTeamStepCounter(StepCounterDTO stepCounterDto);

    void deleteStepCounter(StepCounter stepCounter);

    Optional<StepCounterDTO> getStepCounterById(Long id);

    Optional<List<StepCounterDTO>> getAllTeamScoreDesc();


}
