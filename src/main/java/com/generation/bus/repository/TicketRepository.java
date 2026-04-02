package com.generation.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

    // Permette al bigliettaio di vedere solo i suoi ticket emessi
    List<Ticket> findByPortalUserId(Long userId);


}
