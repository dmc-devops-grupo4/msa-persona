package edu.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.TipoPersonaDTO;
import edu.proyecto.entity.ErrorEntity;
import edu.proyecto.service.TipoPersonaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/tipos-persona")
@Tag(name = "Tipo Persona", description = "Api Tipopersona")
public class TipoPersonaController {
    @Autowired
    private TipoPersonaService tipoPersonaService;

    @ExceptionHandler(Exception.class)   ///objeniendo el error de excepcion en forma generica
    private ErrorEntity capturadorErrores(Exception ex){
        //capturando la excepcion - enviar el status en conflicto 409
        log.error("Error al listar tipos de persona: {}", ex.getMessage(), ex);
        ErrorEntity error = new ErrorEntity(HttpStatus.CONFLICT.toString(), "Problema interno :)", "A ocurrido un error: "+ex.getMessage());
        return  error;
    }

    @GetMapping
    public ResponseEntity<List<TipoPersonaDTO>> ListarTipoPersona(){
        long inicio = System.currentTimeMillis();
        log.info("Consultando tipos de persona");

        List<TipoPersonaDTO> lista = tipoPersonaService.listarTipoPersona();

        log.info("Se cargaron {} registros de tipos de persona en {} ms", lista.size(), System.currentTimeMillis() - inicio);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
    
}
