package com.generation.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.Line;

public interface LineRepository extends JpaRepository<Line, Integer> {

}
