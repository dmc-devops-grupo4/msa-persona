package edu.proyecto.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.dto.UbigeoDTO;
import edu.proyecto.mapper.UbigeoMapper;
import edu.proyecto.repository.UbigeoRepository;
import edu.proyecto.service.UbigeoService;

@Service
public class UbigeoServiceImpl implements UbigeoService{
    @Autowired
    private UbigeoRepository ubigeoRepository;
    
    UbigeoMapper mapper = Mappers.getMapper(UbigeoMapper.class);

    @Override
    public List<UbigeoDTO> listarUbigeo() {
        return ubigeoRepository.consultarUbigeoActivos().stream()
                .map(entity -> mapper.ubigeoEntityToUbigeoDto(entity))
                .collect(Collectors.toList());
    }
}
