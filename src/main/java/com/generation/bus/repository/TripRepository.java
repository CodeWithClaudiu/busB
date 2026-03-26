package com.generation.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long>{
    
}
