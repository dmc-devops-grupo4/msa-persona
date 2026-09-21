package edu.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.TipoDocIdentidadDTO;
import edu.proyecto.entity.ErrorEntity;
import edu.proyecto.service.TipoDocIdentidadService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/tipos-doc-identidad")
@Tag(name = "Tipo Doc Identidad", description = "Api Tipodidentidad")
public class TipoDocIdentidadController {

    @Autowired
    private TipoDocIdentidadService tipoDocIdentidadService;

    @ExceptionHandler(Exception.class)
    private ErrorEntity capturadorErrores(Exception ex){
        ErrorEntity error = new ErrorEntity(HttpStatus.CONFLICT.toString(), "Problema interno :)", "A ocurrido un error: "+ex.getMessage());
        return  error;
    }

    @GetMapping
    public ResponseEntity<List<TipoDocIdentidadDTO>> ListarTipoDocIdentidad(){
        return ResponseEntity.status(HttpStatus.OK)
                            .body(tipoDocIdentidadService.listarTipoDocIdentidad());
    }
    
}
