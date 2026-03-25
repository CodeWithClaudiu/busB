package com.generation.bus.dto;

import com.generation.bus.model.Role;

import lombok.Data;

@Data
public class PortalUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String dob;
    private String email; 
    private String password;
    private Role role;
}
