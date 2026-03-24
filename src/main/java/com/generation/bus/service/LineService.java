package com.generation.bus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.bus.dto.LineDTO;
import com.generation.bus.mapper.LineMapper;

import com.generation.bus.repository.LineRepository;

@Service
public class LineService {

    @Autowired
    private LineRepository lineRepo;

    @Autowired
    private LineMapper lineMapper;

    public List<LineDTO> findAll() {
   return lineMapper.toDTOs(lineRepo.findAll());

}
    public LineDTO findById(int id) {
        return lineMapper.toDTO(lineRepo.findById(id).orElse(null));          
}

    public LineDTO save(LineDTO lineDTO){
        return lineMapper.toDTO(lineRepo.save(lineMapper.toEntity(lineDTO)));
    }

    public LineDTO update(LineDTO lineDTO){
        return lineMapper.toDTO(lineRepo.save(lineMapper.toEntity(lineDTO)));
    }

    public void delete(int id){
    if(!lineRepo.existsById(id)){
        throw new RuntimeException("Line not found with id: " + id);
    }
    lineRepo.deleteById(id);
}


}