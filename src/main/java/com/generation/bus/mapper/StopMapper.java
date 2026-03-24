package com.generation.bus.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.bus.dto.StopDTO;
import com.generation.bus.model.Stop;

@Mapper(componentModel = "spring")
public interface StopMapper {
    
    @Mapping(source = "line.id", target = "lineId")
    StopDTO toDto(Stop stop);

    Stop toEntity(StopDTO stopDTO);

    List<StopDTO> toDTOs(List<Stop> stops);
}


