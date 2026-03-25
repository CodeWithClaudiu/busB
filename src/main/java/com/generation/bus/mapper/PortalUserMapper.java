package com.generation.bus.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.generation.bus.dto.PortalUserDTO;
import com.generation.bus.model.PortalUser;

@Mapper(componentModel = "spring")
public interface PortalUserMapper {

    PortalUserDTO toDTO(PortalUser portalUser);
    List<PortalUserDTO> toDTOs(List<PortalUser> portalUsers);
    
    PortalUser toEntity(PortalUserDTO portalUserDTO);
    List<PortalUser> toEntities(List<PortalUserDTO> portalUserDTOs);
}
