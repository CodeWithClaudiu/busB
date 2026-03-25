package com.generation.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.Line;

public interface LineRepository extends JpaRepository<Line, Long> {

     List<Line> findByStopsCity(String city);
     
     List<Line> findByStopsAddress(String address);

}
