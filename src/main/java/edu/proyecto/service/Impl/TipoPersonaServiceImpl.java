package edu.proyecto.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.dto.TipoPersonaDTO;
import edu.proyecto.mapper.TipoPersonaMapper;
import edu.proyecto.repository.TipoPersonaRepository;
import edu.proyecto.service.TipoPersonaService;

@Service
public class TipoPersonaServiceImpl implements TipoPersonaService{
    @Autowired
    private TipoPersonaRepository tipoPersonaRepository;

    TipoPersonaMapper mapper = Mappers.getMapper(TipoPersonaMapper.class);

    @Override
    public List<TipoPersonaDTO> listarTipoPersona() {
        return tipoPersonaRepository.consultarTipoPersonaActivos().stream()
                .map(entity -> mapper.tipoPersonaEntityToTipoPersonaDto(entity))
                .collect(Collectors.toList());
    }

}
