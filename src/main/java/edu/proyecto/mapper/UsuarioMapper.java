package edu.proyecto.mapper;

import org.mapstruct.Mapper;

import edu.proyecto.dto.UsuarioDTO;
import edu.proyecto.entity.UsuarioEntity;

@Mapper
public interface UsuarioMapper {
    UsuarioDTO usuarioEntityToUsuarioDto(UsuarioEntity usuarioEntity);
}
