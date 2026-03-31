package com.generation.bus.dto;



import lombok.Data;

@Data
public class TicketDTO {

    private Long id;
    private double price;
    private long tripId;

}
