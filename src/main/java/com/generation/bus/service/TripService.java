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

        trip.setStart(dto.getStart());
        trip.setDayType(dto.getDayType());
        trip.setSeason(dto.getSeason());

        trip.setLine(
            lineRepo.findById(dto.getLineId())
                .orElseThrow(() -> new EntityNotFoundException("Line not found"))
        );
        trip = tripRepository.save(trip);
        return tripMapper.toDTO(trip);
    }
    public void delete(Long id){
        if(!tripRepository.existsById(id)){
            throw new EntityNotFoundException("Trip not found with id: " + id); 
        }
        tripRepository.deleteById(id);
    }
}
