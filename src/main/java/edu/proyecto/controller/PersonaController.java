package edu.proyecto.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.PersonaDTO;
import edu.proyecto.dto.EditarPersonaRequestDTO;
import edu.proyecto.dto.GuardarPersonaRequestDTO;
import edu.proyecto.entity.ErrorEntity;
import edu.proyecto.entity.PersonaEntity;
import edu.proyecto.service.PersonaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/personas")
@Tag(name = "Persona", description = "Api Persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @ExceptionHandler(Exception.class)
    private ErrorEntity capturadorErrores(Exception ex){
        //capturando la excepcion - enviar el status en conflicto 409
        return new ErrorEntity(HttpStatus.CONFLICT.toString(), "Problema interno :)", "A ocurrido un error: "+ex.getMessage());
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> listarPersonas(){
        return ResponseEntity.status(HttpStatus.OK)                       
                            .body(personaService.listarPersonas());  //retornando la lista de personas en la respuesta del get
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> registrarPersona(@RequestBody GuardarPersonaRequestDTO request) throws URISyntaxException{   
        PersonaDTO personaEntity = personaService.grabarPersona(request); 
        return ResponseEntity.created(new URI("/" + personaEntity.getIdPersona().toString()))
                            .body(personaEntity);
    }

    @GetMapping("/{idPersona}")
    public ResponseEntity<PersonaDTO> obtenerPersona(@PathVariable("idPersona") Integer id){
        return ResponseEntity.ok(personaService.obtenerPersona(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<PersonaDTO> buscarPersona(
        @RequestParam(required = true) Integer idTipoPersona, 
        @RequestParam(required = true) Integer idTipoDocIdentidad, 
        @RequestParam(required = true) String nroDocumento
    ){
        return ResponseEntity.status(HttpStatus.OK).body(personaService.buscarPersona(idTipoPersona, idTipoDocIdentidad, nroDocumento));
    }

    @PutMapping("/{idPersona}")
    public ResponseEntity<PersonaDTO> actualizarPersona(@PathVariable("idPersona") Integer idPersona, @RequestBody EditarPersonaRequestDTO request){
        return ResponseEntity.ok(personaService.actualizarPersona(idPersona, request));
    }

    @DeleteMapping("/{idPersona}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> eliminarPersona(@PathVariable("idPersona") Integer id){
        personaService.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }

}
