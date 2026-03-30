package com.generation.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.Line;

public interface LineRepository extends JpaRepository<Line, Long> {

    // Trova linee che hanno una fermata con città O indirizzo che contengono la stringa cercata
    List<Line> findDistinctByStopsCityContainingIgnoreCaseOrStopsAddressContainingIgnoreCase(String city, String address);
}
