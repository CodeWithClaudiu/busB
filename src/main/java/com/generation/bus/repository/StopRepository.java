package com.generation.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.Stop;

public interface StopRepository extends JpaRepository<Stop, Long> {

    List<Stop> findByLineIdOrderByPositionAsc(Long lineId);
    
}
