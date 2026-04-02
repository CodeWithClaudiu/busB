package com.generation.bus.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.bus.dto.StopDTO;
import com.generation.bus.dto.TicketDTO;
import com.generation.bus.service.TicketService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketAPI {

    @Autowired private TicketService ticketService;

    // GET ALL: Per l'ADMIN (this.ticketService.getAll())
    @GetMapping
    public List<TicketDTO> getAll() {
        return ticketService.getAll();
    }

    // GET BY USER: Per il BIGLIETTAIO (this.ticketService.getByUser(id))
    @GetMapping("/user/{userId}")
    public List<TicketDTO> getByUser(@PathVariable Long userId) {
        return ticketService.getByUser(userId);
    }

    // POST: Creazione (this.ticketService.create(data))
    @PostMapping
    public TicketDTO create(@RequestBody TicketDTO dto) {
        return ticketService.create(dto);
    }

    // DELETE: (this.ticketService.delete(id))
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }




}
