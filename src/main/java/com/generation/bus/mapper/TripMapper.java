package com.generation.bus.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.generation.bus.dto.TripDTO;
import com.generation.bus.model.Trip;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(source = "line.id", target = "lineId")
    @Mapping(source = "line.stops", target = "stops")
    TripDTO toDTO(Trip trip);

    List<TripDTO> toDTOs(List<Trip> trips);
    
    Trip toEntity(TripDTO tripDTO);
    List<Trip> toEntities(List<TripDTO> tripDTOs);
    
}
