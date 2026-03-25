package com.generation.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.bus.model.PortalUser;

public interface PortalUserRepository extends JpaRepository<PortalUser, Long> {
    
}
