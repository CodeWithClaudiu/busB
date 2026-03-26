package com.generation.bus.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.bus.dto.LoginDTO;
import com.generation.bus.dto.PortalUserDTO;
import com.generation.bus.dto.TokenDTO;
import com.generation.bus.service.PortalUserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class PortalUserAPI {
    
     @Autowired
    private PortalUserService portalUserService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<PortalUserDTO> users = portalUserService.findAll();
            return ResponseEntity.ok(users); 
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error"); 
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            PortalUserDTO user = portalUserService.findById(id);
            return ResponseEntity.ok(user); 
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage()); 
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error"); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        try {
            portalUserService.deleteById(id);
            return ResponseEntity.ok("User deleted successfully"); 
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("User not found"); 
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error"); 
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO dto) {
        try {
            TokenDTO token = portalUserService.login(dto);
            return ResponseEntity.ok(token); 
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage()); 
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error"); 
        }
    }
}
