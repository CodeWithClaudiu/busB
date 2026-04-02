package com.generation.bus.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.generation.bus.dto.TicketDTO;
import com.generation.bus.mapper.TicketMapper;
import com.generation.bus.model.Ticket;
import com.generation.bus.repository.PortalUserRepository;
import com.generation.bus.repository.TicketRepository;
import com.generation.bus.repository.TripRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Validated
public class TicketService {

@Autowired private TicketRepository ticketRepo;
    @Autowired private TripRepository tripRepo;
    @Autowired private PortalUserRepository userRepo;

    // GET ALL: Per l'ADMIN (this.ticketService.getAll())
    @GetMapping
    public List<TicketDTO> getAll() {
        return ticketRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // GET BY USER: Per il BIGLIETTAIO (this.ticketService.getByUser(id))
    @GetMapping("/user/{userId}")
    public List<TicketDTO> getByUser(@PathVariable Long userId) {
        return ticketRepo.findByPortalUserId(userId).stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    // POST: Creazione (this.ticketService.create(data))
    @PostMapping
    public TicketDTO create(@RequestBody TicketDTO dto) {
        Ticket t = new Ticket();
        t.setDate(dto.getDate());
        t.setTrip(tripRepo.findById(dto.getTripId()).orElse(null));
        t.setPortalUser(userRepo.findById(dto.getUserId()).orElse(null));
        
        Ticket saved = ticketRepo.save(t);
        return convertToDTO(saved);
    }

    // DELETE: (this.ticketService.delete(id))
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ticketRepo.deleteById(id);
    }

    // Metodo helper per mappare l'entità nel DTO che il frontend si aspetta
    private TicketDTO convertToDTO(Ticket t) {
        TicketDTO dto = new TicketDTO();
        dto.setId(t.getId());
        dto.setDate(t.getDate());
        dto.setTripId(t.getTrip() != null ? t.getTrip().getId() : null);
        dto.setUserId(t.getPortalUser() != null ? t.getPortalUser().getId() : null);
        return dto;
    }

}



