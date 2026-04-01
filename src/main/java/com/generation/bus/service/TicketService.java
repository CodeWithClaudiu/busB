package com.generation.bus.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.generation.bus.dto.TicketDTO;
import com.generation.bus.mapper.TicketMapper;
import com.generation.bus.model.Ticket;
import com.generation.bus.repository.TicketRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Validated
public class TicketService {


    @Autowired
    TicketRepository ticketRepo;

    @Autowired
    TicketMapper ticketMapper;


    @Autowired
    PortalUserService portalUserService;

    private static final double FIXED_PRICE = 2.00;
    private static final int VALIDITY_MINUTES = 90;

    public List<TicketDTO> findAll() {
        return ticketMapper.toDTOs(ticketRepo.findAll());
    }

    public TicketDTO findById(Long id) {
        Ticket t = ticketRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket non trovato con ID: " + id));
        return ticketMapper.toDTO(t);
    }
    public TicketDTO save(TicketDTO dto) {
            Ticket ticket = ticketMapper.toEntity(dto);
            ticket.setPrice(FIXED_PRICE); // Forza sempre i 2 euro
            
            LocalDateTime now = LocalDateTime.now();
            ticket.setDate(now.toLocalDate()); // Imposta data di oggi
            ticket.setHour(now.getHour());     // Imposta ora attuale
            
            return ticketMapper.toDTO(ticketRepo.save(ticket));
        }
    public TicketDTO update(Long id, TicketDTO dto) {
        // 1. Recuperiamo il ticket esistente (se non c'è, lancia l'eccezione)
        Ticket existing = ticketRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Impossibile aggiornare: Ticket non trovato"));
        
        // 2. Mappiamo il DTO in una nuova entità per l'aggiornamento
        Ticket updated = ticketMapper.toEntity(dto);
        
        // 3. USIAMO 'existing' per proteggere i dati che non devono cambiare
        updated.setId(existing.getId());         
        updated.setDate(existing.getDate());      
        updated.setHour(existing.getHour());       
        updated.setPrice(FIXED_PRICE);             

        return ticketMapper.toDTO(ticketRepo.save(updated));
    }

    public void delete(Long id) {
        if (!ticketRepo.existsById(id)) {
            throw new EntityNotFoundException("Impossibile eliminare: Ticket non trovato");
        }
        ticketRepo.deleteById(id);
    }

    // --- METODO PER VERIFICARE LA VALIDITÀ ---
    public boolean isTicketValid(Long id) {
        Ticket t = ticketRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket non trovato"));

        // Ricostruiamo il momento di emissione (Data + Ora:00)
        LocalDateTime issuedAt = LocalDateTime.of(t.getDate(), LocalTime.of(t.getHour(), 0));
        
        // Calcoliamo la scadenza aggiungendo i 90 minuti della costante
        LocalDateTime expiryTime = issuedAt.plusMinutes(VALIDITY_MINUTES);

        // Il biglietto è valido se "adesso" è prima della scadenza
        return LocalDateTime.now().isBefore(expiryTime);
    }


}



