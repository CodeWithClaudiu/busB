package com.generation.bus.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LineDTO {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    private List<StopDTO> stops;


}
