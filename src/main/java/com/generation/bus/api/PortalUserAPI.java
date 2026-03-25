package com.generation.bus.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.bus.dto.PortalUserDTO;
import com.generation.bus.service.PortalUserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class PortalUserAPI {
    
    @Autowired
    private PortalUserService portalUserService;

     @GetMapping
    public List<PortalUserDTO> findAll() {
        return portalUserService.findAll();
    }

    @GetMapping("/{id}")
    public PortalUserDTO findById(@PathVariable Long id) {
        return portalUserService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        portalUserService.deleteById(id);
    }
}
