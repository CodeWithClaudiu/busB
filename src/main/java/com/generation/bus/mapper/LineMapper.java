package com.generation.bus.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.generation.bus.dto.LineDTO;
import com.generation.bus.model.Line;

@Mapper(componentModel = "spring")
public interface LineMapper {

  LineDTO toDTO(Line line);
List<LineDTO> toDTOs(List<Line> lines);

Line toEntity(LineDTO lineDTO);
List<Line> toEntities(List<LineDTO> lineDTOs);
}
