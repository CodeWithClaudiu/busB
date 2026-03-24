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
import org.springframework.web.bind.annotation.RestController;

import com.generation.bus.dto.LineDTO;
import com.generation.bus.service.LineService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/lines")
public class LineAPI {

    @Autowired
    LineService lineService;

   @GetMapping
   public List<LineDTO> findAll(){
    return lineService.findAll();
   }

   @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        LineDTO dto = lineService.findById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(404).body("Prodotto non trovato");
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
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody LineDTO dto) {
        try {
            dto = lineService.update(dto);
            return ResponseEntity.status(200).body(dto);
        }catch(ConstraintViolationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        lineService.delete(id);
        return ResponseEntity.noContent().build();
    }




   
}
