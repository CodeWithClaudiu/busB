package com.generation.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.Line;

public interface LineRepository extends JpaRepository<Line, Long> {

    // Trova le linee che hanno almeno una fermata in una determinata città
    List<Line> findByStopsCity(String city);

    // Trova le linee che hanno almeno una fermata in una determinata via
    List<Line> findByStopsAddress(String address);
}
