package com.generation.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.generation.bus.dto.StopDTO;
import com.generation.bus.mapper.StopMapper;
import com.generation.bus.model.Stop;
import com.generation.bus.repository.LineRepository;
import com.generation.bus.repository.StopRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Validated
public class StopService {
    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private StopMapper stopMapper;

    @Autowired
    private LineRepository lineRepo;

    public List<StopDTO> findAll() {
        return stopMapper.toDTOs(stopRepository.findAll());
    }

    public StopDTO findById(Long id) {
        Stop stop = stopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stop not found with id: " + id));
        return stopMapper.toDto(stop);
    }

    // INSERIMENTO CON SHIFT
    @Transactional
    public StopDTO save(@Valid StopDTO dto) {
        // A. Sposta le fermate esistenti in "parcheggio" negativo
        stopRepository.shiftToNegative(dto.getLineId(), dto.getPosition());
        stopRepository.flush(); // Applica subito

        // B. Riporta le fermate in positivo scalate di 1
        stopRepository.shiftFromNegativeToPositive(dto.getLineId());
        stopRepository.flush(); // Libera definitivamente le posizioni originali

        // C. Ora la posizione (es. 2) è matematicamente libera nel DB. Procedi al salvataggio.
        Stop stop = stopMapper.toEntity(dto);
        stop.setLine(lineRepo.findById(dto.getLineId())
                .orElseThrow(() -> new EntityNotFoundException("Line not found")));

        stop = stopRepository.save(stop);
        return stopMapper.toDto(stop);
    }

    public StopDTO update(Long id, @Valid StopDTO dto) {
        Stop stop = stopRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Stop not found with id: " + id));

        stop.setCity(dto.getCity());
        stop.setAddress(dto.getAddress());
        stop.setPosition(dto.getPosition());
        stop.setTime(dto.getTime());

        stop.setLine(lineRepo.findById(dto.getLineId())
                .orElseThrow(() -> new EntityNotFoundException("Line not found")));

        stop = stopRepository.save(stop);
        return stopMapper.toDto(stop);
    }

    // CANCELLAZIONE CON SHIFT (CHIUDI IL BUCO)
    public void deleteById(Long id) {
        Stop stop = stopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stop non trovata"));

        Long lineId = stop.getLine().getId();
        Integer posDeleted = stop.getPosition();

        // 1. Eliminiamo la fermata
        stopRepository.delete(stop);
        
        // Forza l'eliminazione prima di scalare le altre
        stopRepository.flush(); 

        // 2. Scaliamo indietro tutte le fermate successive per chiudere il buco
        stopRepository.shiftPositionsBackward(lineId, posDeleted);
    }
}
