package com.generation.bus.model;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"line_id", "position"}),
        @UniqueConstraint(columnNames = {"line_id", "address"})
    }
)
public class Stop {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;  

    
    private String address;   
    
    private Integer position; 

    private int time;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line line;
}
