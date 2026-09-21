package edu.proyecto.mapper;

import org.mapstruct.Mapper;

import edu.proyecto.dto.UbigeoDTO;
import edu.proyecto.entity.UbigeoEntity;

@Mapper
public interface UbigeoMapper {
    UbigeoDTO ubigeoEntityToUbigeoDto(UbigeoEntity ubigeoEntity);
}
