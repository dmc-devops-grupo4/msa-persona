package edu.proyecto.mapper;

import org.mapstruct.Mapper;

import edu.proyecto.dto.TipoDocIdentidadDTO;
import edu.proyecto.entity.TipoDocIdentidadEntity;

@Mapper
public interface TipoDocIdentidadMapper {    
    TipoDocIdentidadDTO tipoDocIdentidadEntityToTipoDocIdentidadDto(TipoDocIdentidadEntity tipoDocIdentidadEntity);
}
