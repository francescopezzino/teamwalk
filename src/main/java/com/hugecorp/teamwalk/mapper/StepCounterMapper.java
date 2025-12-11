package com.hugecorp.teamwalk.mapper;

import com.hugecorp.teamwalk.domain.StepCounter;
import com.hugecorp.teamwalk.model.StepCounterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StepCounterMapper {

    @Mapping(target = "teamId", source = "team.id")
    StepCounterDTO toStepCounterDto(StepCounter entity);

    @Mapping(target = "id", ignore = true)
    StepCounter toStepCounterEntity(StepCounterDTO dto);

    List<StepCounterDTO> toStepCounterDtos(Collection<StepCounter> stepCounter);
}
