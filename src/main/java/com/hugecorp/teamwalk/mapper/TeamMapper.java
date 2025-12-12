package com.hugecorp.teamwalk.mapper;

import com.hugecorp.teamwalk.domain.Team;
import com.hugecorp.teamwalk.model.TeamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDTO toTeamDto(Team entity);

    Team toTeamEntity(TeamDTO dto);

}
