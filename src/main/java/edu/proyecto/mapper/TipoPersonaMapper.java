package edu.proyecto.mapper;

import org.mapstruct.Mapper;

import edu.proyecto.dto.TipoPersonaDTO;
import edu.proyecto.entity.TipoPersonaEntity;

@Mapper
public interface TipoPersonaMapper {
    TipoPersonaDTO tipoPersonaEntityToTipoPersonaDto(TipoPersonaEntity tipoPersonaEntity);
}