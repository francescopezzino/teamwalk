package com.hugecorp.teamwalk.model;

public record StepCounterDTO(Long id, String name, Long teamId, Integer steps) {

    public StepCounterDTO {
        if (steps == null) {
            steps = 0;
        }
    }
}
