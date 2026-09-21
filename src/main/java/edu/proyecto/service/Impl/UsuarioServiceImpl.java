package edu.proyecto.service.Impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.dto.UsuarioDTO;
import edu.proyecto.entity.PersonaEntity;
import edu.proyecto.entity.UsuarioEntity;
import edu.proyecto.mapper.UsuarioMapper;
import edu.proyecto.repository.PersonaRepository;
import edu.proyecto.repository.UsuarioRepository;
import edu.proyecto.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository; 
    @Autowired
    private PersonaRepository personaRepository;

    UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);

    @Override
    public UsuarioDTO validarUsuario(Integer idTipoPersona, Integer idTipoDocIdentidad, String nroDocumento, String password) {

        PersonaEntity persona = personaRepository.buscarPersona(idTipoPersona, idTipoDocIdentidad, nroDocumento);

        if(persona == null){
            throw new RuntimeException("La persona no existe");
        }

        UsuarioEntity usuario = usuarioRepository.buscarUsuarioPorIdPersona(persona.getIdPersona());

        if(usuario == null){
            throw new RuntimeException("El usuario no existe");
        }

        if(!usuario.getPassword().equals(password)){
            throw new RuntimeException("Las credenciales ingresadas son incorrectas");
        }

        if(Boolean.FALSE.equals(usuario.getVerificado())){
            throw new RuntimeException("El usuario no ha sido verificado");
        }

        UsuarioDTO usuarioDto = mapper.usuarioEntityToUsuarioDto(usuario);
        usuarioDto.setIdPersona(persona.getIdPersona());
        usuarioDto.setNombres(persona.getNombres());
        usuarioDto.setApellidoPaterno(persona.getApellidoPaterno());
        usuarioDto.setApellidoMaterno(persona.getApellidoMaterno());
        usuarioDto.setNroDocumento(persona.getNroDocumento());

        return usuarioDto;
    }

}
