package com.generation.bus.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.bus.dto.TripDTO;
import com.generation.bus.model.Trip;
import com.generation.bus.service.TripService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = "http://localhost:4200")
public class TripAPI {

    @Autowired
    private TripService tripService;

    @GetMapping
    public List<TripDTO> findAll() {
        return tripService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try { 
            TripDTO dto = tripService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Trip not found");
        }
    } 
    
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody TripDTO dto){
        try {
            dto = tripService.save(dto);
            return ResponseEntity.status(201).body(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody TripDTO dto){
        try {
            dto.setId(id);
            dto = tripService.update(id, dto);
            return ResponseEntity.ok(dto);
        }catch (ConstraintViolationException e ) {
            return ResponseEntity.status(400).body(e.getMessage());
        }catch (EntityNotFoundException e ) {
            return ResponseEntity.status(404).body("Trip not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        tripService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
