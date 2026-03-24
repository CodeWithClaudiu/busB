package com.generation.bus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.generation.bus.dto.StopDTO;
import com.generation.bus.mapper.StopMapper;
import com.generation.bus.model.Stop;
import com.generation.bus.repository.StopRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
@Validated
public class StopService {
    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private StopMapper stopMapper;

    public List<StopDTO> findAll() {
        return stopMapper.toDTOs(stopRepository.findAll());
    }

    public StopDTO findById(Long id) {
        Stop stop = stopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stop not found with id: " + id));
        return stopMapper.toDto(stop);
    }

    public StopDTO save(@Valid StopDTO dto) {
        Stop stop = stopMapper.toEntity(dto);
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

        stop = stopRepository.save(stop);
        return stopMapper.toDto(stop);
    }

    public void deleteById(Long id) {
        stopRepository.deleteById(id);
    }
}
