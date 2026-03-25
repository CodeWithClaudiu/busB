package com.generation.bus.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class PortalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Date of birth is required")
    private String dob;

    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;
    
    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private Role role;

}
