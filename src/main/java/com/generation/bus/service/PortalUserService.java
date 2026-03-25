package com.generation.bus.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.generation.bus.dto.PortalUserDTO;
import com.generation.bus.mapper.PortalUserMapper;
import com.generation.bus.model.PortalUser;
import com.generation.bus.repository.PortalUserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Validated
public class PortalUserService {
    
    @Autowired
    private PortalUserRepository portalUserRepository;

    @Autowired
    private PortalUserMapper portalUserMapper;


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





}
