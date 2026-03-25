package com.generation.bus.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.bus.dto.LineDTO;
import com.generation.bus.mapper.LineMapper;
import com.generation.bus.model.Line;
import com.generation.bus.repository.LineRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LineService {
    @Autowired
    private LineRepository lineRepo;

    @Autowired
    private LineMapper lineMapper;

    public List<LineDTO> findAll() {
        return lineMapper.toDTOs(lineRepo.findAll());
    }

    public LineDTO findById(Long id) {
        return lineMapper.toDTO(
            lineRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Line not found with id: " + id))
        );
    }

    public LineDTO save(LineDTO lineDTO) {
        return lineMapper.toDTO(lineRepo.save(lineMapper.toEntity(lineDTO)));
    }

    public LineDTO update(LineDTO lineDTO) {
        return lineMapper.toDTO(lineRepo.save(lineMapper.toEntity(lineDTO)));
    }

    public void delete(Long id) {
        if (!lineRepo.existsById(id)) {
            throw new EntityNotFoundException("Line not found with id: " + id);
        }
        lineRepo.deleteById(id);
    }

    public List<LineDTO> findByAddress(String address){
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("L'indirizzo di ricerca non può essere vuoto");
        }
        List<Line> lines = lineRepo.findByStopsAddress(address);
        return lineMapper.toDTOs(lines);
    }
   public List<LineDTO> findByCity(String city) { 
   
    if (city == null || city.trim().isEmpty()) {
        throw new IllegalArgumentException("City name is required");
    }

    // 2. Recupero i dati dal DB (Entità)
    List<Line> lines = lineRepo.findByStopsCity(city);

    // 3. Trasformo le entità in DTO e restituisco
    return lineMapper.toDTOs(lines);
}


}