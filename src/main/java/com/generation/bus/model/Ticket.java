package com.generation.bus.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDat date;

    // Relazione con la corsa (Trip)
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    // Relazione con l'utente (PortalUser)
    @ManyToOne
    @JoinColumn(name = "portal_user_id")
    private PortalUser portalUser;

}
