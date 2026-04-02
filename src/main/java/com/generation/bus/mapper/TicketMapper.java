package com.generation.bus.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.generation.bus.dto.TicketDTO;
import com.generation.bus.model.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDTO toDTO(Ticket ticket);
    List<TicketDTO> toDTOs(List<Ticket> tickets);
    
    Ticket toEntity(TicketDTO ticketDTO);
    List<Ticket> toEntities(List<TicketDTO> ticketDTOs);

}
