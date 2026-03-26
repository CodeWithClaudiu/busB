package com.generation.bus.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.generation.bus.dto.LoginDTO;
import com.generation.bus.dto.PortalUserDTO;
import com.generation.bus.dto.TokenDTO;
import com.generation.bus.mapper.PortalUserMapper;
import com.generation.bus.model.PortalUser;
import com.generation.bus.repository.PortalUserRepository;
import com.generation.bus.security.JwtService;
import com.generation.bus.security.PasswordHasher;

import jakarta.persistence.EntityNotFoundException;

@Service
@Validated
public class PortalUserService {
    
@Autowired
    private PortalUserRepository portalUserRepository;

    @Autowired
    private PortalUserMapper portalUserMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordHasher passwordHasher;

    public List<PortalUserDTO> findAll() {
        return portalUserMapper.toDTOs(portalUserRepository.findAll());
    }

    public PortalUserDTO findById(Long id) {
        PortalUser user = portalUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PortalUser not found with id: " + id));
        return portalUserMapper.toDTO(user);
    }

    public void deleteById(Long id) {
        portalUserRepository.deleteById(id);
    }

    //LOGIN
    public TokenDTO login(LoginDTO loginDTO) {

        PortalUser user = portalUserRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // hash della password inserita
        String hashedInput = passwordHasher.toMD5(loginDTO.getPassword());

        // confronto con quella salvata nel DB
        if (!hashedInput.equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // generazione token
        String token = jwtService.generateToken(user);

        return new TokenDTO(token);
    }
}
