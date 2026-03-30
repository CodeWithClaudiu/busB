package com.generation.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.generation.bus.dto.TripDTO;
import com.generation.bus.mapper.TripMapper;
import com.generation.bus.model.Trip;
import com.generation.bus.repository.LineRepository;
import com.generation.bus.repository.TripRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
@Validated
public class TripService {
    
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private LineRepository lineRepo;

    public List<TripDTO> findAll() {
        return tripMapper.toDTOs(tripRepository.findAll());
    }

    public TripDTO findById(Long id) {
        Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));
            return tripMapper.toDTO(trip);
    }

    public TripDTO save(@Valid TripDTO dto) {
        Trip trip = tripMapper.toEntity(dto);
        trip.setLine(
            lineRepo.findById(dto.getLineId())
                .orElseThrow(() -> new EntityNotFoundException("Line not found"))
        );
        trip = tripRepository.save(trip);
        return tripMapper.toDTO(trip);
    }

    public TripDTO update(Long id, @Valid TripDTO dto) {
        Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));

            // Validazione
        if (dto.getTrafficMultiplier() < 0) {
            throw new IllegalArgumentException("The traffic multiplier cannot be negative");
        }

        trip.setStart(dto.getStart());
        trip.setDayType(dto.getDayType());
        trip.setSeason(dto.getSeason());
        trip.setDate(dto.getDate());
        trip.setTrafficMultiplier(dto.getTrafficMultiplier());

        trip.setLine(
            lineRepo.findById(dto.getLineId())
                .orElseThrow(() -> new EntityNotFoundException("Line not found"))
        );
        trip = tripRepository.save(trip);
        return updateAndSaveTrip(trip); // Usiamo il metodo con il calcolo
    }

private TripDTO updateAndSaveTrip(Trip trip) {
    
    // 1. Verifichiamo che i dati esistano nell'Entity
    if (trip.getLine() != null && trip.getLine().getStops() != null) {
        
        for (int i = 0; i < trip.getLine().getStops().size(); i++) {
            // Prendiamo la fermata direttamente dall'Entity
            var stop = trip.getLine().getStops().get(i);
            
            int minutiBase = stop.getTime();
            int minutiAggiornati = (int) Math.round(minutiBase * (1 + trip.getTrafficMultiplier()));

            // 2. MODIFICA FONDAMENTALE: Settiamo il valore nell'ENTITY (non nel DTO!)
            // Questo cambia l'oggetto che Spring "conosce" come riga del database
            stop.setTime(minutiAggiornati);
        }

        // 3. PERSISTENZA: Salviamo l'intero oggetto trip (con le fermate aggiornate) nel DB
        tripRepository.save(trip);
    }

    // 4. Infine, trasformiamo l'entity GIÀ AGGIORNATA in DTO per restituirla
    return tripMapper.toDTO(trip);
}

    public void delete(Long id){
        if(!tripRepository.existsById(id)){
            throw new EntityNotFoundException("Trip not found with id: " + id); 
        }
        tripRepository.deleteById(id);
    }
}
