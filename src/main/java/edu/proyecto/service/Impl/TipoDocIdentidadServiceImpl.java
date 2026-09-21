package edu.proyecto.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.dto.TipoDocIdentidadDTO;
import edu.proyecto.mapper.TipoDocIdentidadMapper;
import edu.proyecto.repository.TipoDocIdentidadRepository;
import edu.proyecto.service.TipoDocIdentidadService;

@Service
public class TipoDocIdentidadServiceImpl implements TipoDocIdentidadService{
    @Autowired
    private TipoDocIdentidadRepository tipoDocIdentidadRepository;
    
    TipoDocIdentidadMapper mapper = Mappers.getMapper(TipoDocIdentidadMapper.class);

    @Override
    public List<TipoDocIdentidadDTO> listarTipoDocIdentidad() {
        return tipoDocIdentidadRepository.consultarTipoDocIdentidadActivos().stream()
                .map(entity -> mapper.tipoDocIdentidadEntityToTipoDocIdentidadDto(entity))
                .collect(Collectors.toList());
    }
    
}
