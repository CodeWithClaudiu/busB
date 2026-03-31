package com.generation.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.UUID;
import com.generation.bus.dto.TicketDTO;
import com.generation.bus.mapper.TicketMapper;
import com.generation.bus.model.Ticket;
import com.generation.bus.model.Trip;
import com.generation.bus.repository.TicketRepository;
import com.generation.bus.repository.TripRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
@Validated
public class TicketService {

    @Autowired
    TicketRepository ticketRepo;

    @Autowired
    TicketMapper ticketMapper;

    @Autowired
    TripRepository tripRepository;

    
// Prezzo fisso deciso dal backend
    private static final double FIXED_PRICE = 2.00;

    // --- FIND ALL ---
    public List<TicketDTO> findAll() {
        return ticketMapper.toDTOs(ticketRepo.findAll());
    }

    // --- FIND BY ID ---
    public TicketDTO findById(Long id) {
        Ticket ticket = ticketRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
        return ticketMapper.toDTO(ticket);
    }

    // --- CREATE (Emissione Biglietto a Bordo) ---
    public TicketDTO save(@Valid TicketDTO dto) {
        Ticket ticket = new Ticket();

       
        ticket.setPrice(FIXED_PRICE);

        // Colleghiamo il viaggio
        Trip trip = tripRepository.findById(dto.getTripId())
            .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + dto.getTripId()));
        
        ticket.setTrip(trip);

        ticket = ticketRepo.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    // --- UPDATE ---
    public TicketDTO update(Long id, @Valid TicketDTO dto) {
        Ticket ticket = ticketRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));

        Trip trip = tripRepository.findById(dto.getTripId())
            .orElseThrow(() -> new EntityNotFoundException("Trip not found"));
        
        ticket.setTrip(trip);

        ticket = ticketRepo.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    // --- DELETE ---
    public void delete(Long id) {
        if (!ticketRepo.existsById(id)) {
            throw new EntityNotFoundException("Biglietto non trovato con id: " + id);
        }
        ticketRepo.deleteById(id);
    }
}










