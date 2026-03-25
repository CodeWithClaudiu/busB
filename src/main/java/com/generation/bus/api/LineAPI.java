package com.generation.bus.api;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.bus.dto.LineDTO;
import com.generation.bus.service.LineService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/lines")
public class LineAPI {

   @Autowired
    private LineService lineService;

    @GetMapping
    public List<LineDTO> findAll() {
        return lineService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            LineDTO dto = lineService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Linea non trovata");
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody LineDTO dto) {
        try {
            dto = lineService.save(dto);
            return ResponseEntity.status(201).body(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody LineDTO dto) {
        try {
            dto.setId(id);
            dto = lineService.update(dto);
            return ResponseEntity.ok(dto);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Linea non trovata");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/address")
    public ResponseEntity<Object> findByAddress(@RequestParam String address){
        try {
            List<LineDTO> results = lineService.findByAddress(address);
            return ResponseEntity.ok(results);
        }catch(IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
        @GetMapping("/search/city")
        public ResponseEntity<Object> findByCity(@RequestParam String city) {
        try {
            List<LineDTO> dtos = lineService.findByCity(city);
            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            // Se il nome città è vuoto o null (come abbiamo impostato nel Service)
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            // Se decidi di lanciare questa eccezione quando non trovi linee
            return ResponseEntity.status(404).body("Nessuna linea trovata per questa città");
        } catch (Exception e) {
            // Per qualsiasi altro errore imprevisto
            return ResponseEntity.status(500).body("Errore interno del server");
        }
}
   
    
}
