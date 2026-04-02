package com.generation.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{


}
