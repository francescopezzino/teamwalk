package com.hugecorp.teamwalk.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record StepCounterDTO(@Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id, String name, Long teamId, Integer steps) {

    public StepCounterDTO {
        if (steps == null) {
            steps = 0;
        }
    }
}
