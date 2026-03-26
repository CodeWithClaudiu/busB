package com.generation.bus.dto;

import java.time.LocalTime;
import java.util.List;

import com.generation.bus.model.DayType;
import com.generation.bus.model.Season;

import lombok.Data;

@Data
public class TripDTO 
{

    private long id;

    private LocalTime start;

    private DayType dayType;

    private Season season;

    private long lineId;
    private List<StopDTO> stops;

}
