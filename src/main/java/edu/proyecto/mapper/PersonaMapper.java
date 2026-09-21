package edu.proyecto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.proyecto.dto.PersonaDTO;
import edu.proyecto.dto.GuardarPersonaRequestDTO;
import edu.proyecto.entity.PersonaEntity;

@Mapper
public interface PersonaMapper {
    // @Mapping(source = "", target = "")
    PersonaDTO personaEntityToPersonaDto(PersonaEntity personaEntity);
    PersonaEntity personaRequestDtoToPersonaEntity(GuardarPersonaRequestDTO personaRequestDTO);
}
