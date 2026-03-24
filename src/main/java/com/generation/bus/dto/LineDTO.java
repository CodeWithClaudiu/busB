package com.generation.bus.dto;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class LineDTO {

    long id;
    private String name;
    List<StopDTO> stops;


}
