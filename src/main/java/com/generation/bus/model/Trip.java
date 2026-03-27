package com.generation.bus.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Trip 
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime start;
    
    @Enumerated(EnumType.STRING)
    private DayType dayType;

    @Enumerated(EnumType.STRING)
    private Season season;

    private LocalDate date;

    private double trafficMultiplier = 1.0;


    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line line;
}
