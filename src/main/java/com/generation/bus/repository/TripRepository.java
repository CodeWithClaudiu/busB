package com.generation.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long>{
    
    List<Trip> findByTrafficMultiplierGreaterThan(Double value);
}
