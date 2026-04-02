package com.generation.bus.dto;

import lombok.Data;

@Data
public class TicketDTO {

    private Long id;
    private String date;
    private int hour;
    private Long portalUserId;


}
