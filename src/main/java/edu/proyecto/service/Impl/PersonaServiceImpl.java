package edu.proyecto.service.Impl;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import edu.proyecto.dto.PersonaDTO;
import edu.proyecto.dto.EditarPersonaRequestDTO;
import edu.proyecto.dto.EmailRegistroPersonaDTO;
import edu.proyecto.dto.GuardarPersonaRequestDTO;
import edu.proyecto.entity.PersonaEntity;
import edu.proyecto.entity.TipoDocIdentidadEntity;
import edu.proyecto.entity.TipoPersonaEntity;
import edu.proyecto.entity.UbigeoEntity;
import edu.proyecto.entity.UsuarioEntity;
import edu.proyecto.entity.enums.TipoPersonaEnum;
import edu.proyecto.mapper.PersonaMapper;
import edu.proyecto.repository.PersonaRepository;
import edu.proyecto.repository.TipoDocIdentidadRepository;
import edu.proyecto.repository.TipoPersonaRepository;
import edu.proyecto.repository.UbigeoRepository;
import edu.proyecto.repository.UsuarioRepository;
import edu.proyecto.service.PersonaService;
import edu.proyecto.utils.Helper;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private UbigeoRepository ubigeoRepository;
    @Autowired
    private TipoPersonaRepository tipoPersonaRepository;
    @Autowired
    private TipoDocIdentidadRepository tipoDocIdentidadRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${topico.registro-persona}")
    private String topicoRegistroPersona;

    PersonaMapper mapper = Mappers.getMapper(PersonaMapper.class);

    @Override
    public List<PersonaDTO> listarPersonas() {
        return personaRepository.listarPersonasActivas().stream()
                .map(entity -> mapper.personaEntityToPersonaDto(entity))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED) 
    public PersonaDTO grabarPersona(GuardarPersonaRequestDTO personaDto)  {

        UbigeoEntity ubigeoEntity = ubigeoRepository.findById(personaDto.getCodUbigeo())
                                    .orElseThrow(() -> new RuntimeException("Ubigeo no encontrado"));

        TipoPersonaEntity tipoPersonaEntity = tipoPersonaRepository.findById(personaDto.getIdTipoPersona())
                                    .orElseThrow(() -> new RuntimeException("Tipo Persona no encontrado"));

        TipoDocIdentidadEntity tipoDocIdentidadEntity = tipoDocIdentidadRepository.findById(personaDto.getIdTipoDocIdentidad())
                                    .orElseThrow(() -> new RuntimeException("Tipo Documento Identidad no encontrado"));

        if(personaDto.getIdTipoPersona() == TipoPersonaEnum.JURIDICA.getValue()){
            if(personaDto.getRuc().length() == 0) {
                throw new RuntimeException("El RUC no puede estar vacío");
            }
            if(personaDto.getRazonSocial().length() == 0) {
                throw new RuntimeException("Razón Social no puede estar vacía");
            }
            if(personaDto.getRuc().length() > 0 && personaDto.getRuc().length() != 11) {
                throw new RuntimeException("EL RUC debe tener 11 dígitos");
            }
        }
        
        // Validar Email:
        PersonaEntity persona = personaRepository.findFirstByCorreoAndActivoTrue(personaDto.getCorreo());
        if (persona != null) {
            throw new RuntimeException("El correo ya está pertenece a un usuario");
        }
    
        Pattern pattern = Pattern.compile("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$");
        Matcher matcherEmail = pattern.matcher(personaDto.getCorreo());
        if(!matcherEmail.matches()){
            throw new RuntimeException("Formato de correo incorrecto");
        }
        
        PersonaEntity personaEntity = mapper.personaRequestDtoToPersonaEntity(personaDto);
    
        personaEntity.setUbigeo(ubigeoEntity);
        personaEntity.setTipoPersona(tipoPersonaEntity);
        personaEntity.setTipoDocIdentidad(tipoDocIdentidadEntity);
        personaEntity.setActivo(true); 
        personaEntity.setRegFechaCreacion(Helper.getCurrentDate());
        personaEntity.setRegUsuarioCreacion("DEFAULT");

        personaEntity = personaRepository.save(personaEntity);

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setPersona(personaEntity);
        usuarioEntity.setPassword(personaDto.getPassword());
        usuarioEntity.setCodigoVerificacion(UUID.randomUUID().toString());
        usuarioEntity.setVerificado(true);
        usuarioEntity.setActivo(true);
        usuarioEntity.setRegFechaCreacion(Helper.getCurrentDate());
        usuarioEntity.setRegUsuarioCreacion("DEFAULT"); 

        usuarioRepository.save(usuarioEntity);

        try {
            String jsonMessage = objectMapper.writeValueAsString(
                new EmailRegistroPersonaDTO(
                    personaEntity.getCorreo(), 
                    personaEntity.getNombres()
                ));
    
            System.out.println("Mensaje: " + jsonMessage);
            kafkaTemplate.send(topicoRegistroPersona, jsonMessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return mapper.personaEntityToPersonaDto(personaEntity);
    }

    @Override
    public PersonaDTO obtenerPersona(Integer idPersona) {
        PersonaEntity personaEntity = personaRepository.findById(idPersona).get();
        return mapper.personaEntityToPersonaDto(personaEntity);
    }

    @Override
    public PersonaDTO buscarPersona(Integer idTipoPersona, Integer idTipoDocIdentidad, String nroDocumento){
        PersonaEntity personaEntity = personaRepository.buscarPersona(idTipoPersona, idTipoDocIdentidad, nroDocumento);
        return mapper.personaEntityToPersonaDto(personaEntity);
    }

    @Override
    public PersonaDTO actualizarPersona(Integer idPersona, EditarPersonaRequestDTO request) {
        PersonaEntity personaEntity = personaRepository.findById(idPersona).get();

        UbigeoEntity ubigeoEntity = ubigeoRepository.findById(request.getCodUbigeo())
                                        .orElseThrow(() -> new RuntimeException("Ubigeo no encontrado"));
        
        personaEntity.setUbigeo(ubigeoEntity);
        personaEntity.setDireccion(request.getDireccion());
        personaEntity.setCorreo(request.getCorreo());
        personaEntity.setCelular(request.getCelular());
        personaEntity.setRegUsuarioModificacion("DEFAULT");
        personaEntity.setRegFechaModificacion(Helper.getCurrentDate());

        personaRepository.save(personaEntity);

        return mapper.personaEntityToPersonaDto(personaEntity);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED) 
    public void eliminarPersona(Integer idPersona) {
        PersonaEntity personaEntity=personaRepository.findById(idPersona).get();
        UsuarioEntity usuario = usuarioRepository.buscarUsuarioPorIdPersona(personaEntity.getIdPersona());

        //Eliminar persona
        personaEntity.setActivo(false);
        personaEntity.setRegUsuarioModificacion("DEFAULT");
        personaEntity.setRegFechaModificacion(Helper.getCurrentDate());
        personaRepository.save(personaEntity);

        //Eliminar usuario
        usuario.setActivo(false);
        usuario.setRegUsuarioModificacion("DEFAULT");
        usuario.setRegFechaModificacion(Helper.getCurrentDate());
        usuarioRepository.save(usuario);
    }

}
