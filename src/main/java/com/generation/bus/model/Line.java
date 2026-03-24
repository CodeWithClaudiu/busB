package com.generation.bus.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Line {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String name;
   

    @OneToMany(mappedBy = "line")
    private List<Stop> stops;


}
