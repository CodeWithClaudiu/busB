package com.generation.bus.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TicketDTO {

    private Long id;
    private Long tripId;
    private Long userId;
    private LocalDate date;
}
