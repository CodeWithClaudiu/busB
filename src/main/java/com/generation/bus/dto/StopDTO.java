package com.generation.bus.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StopDTO {

    private Long id;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "Address is required")
    private String address;
    @NotNull(message = "Position is required")
    private Integer position;
    @NotNull(message = "Time is required")
    private int time;

    private Long lineId;
}
