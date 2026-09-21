package edu.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.UbigeoDTO;
import edu.proyecto.entity.ErrorEntity;
import edu.proyecto.service.UbigeoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/ubigeos")
@Tag(name = "Ubigeo",description = "Api Ubigeo")
public class UbigeoController {
    @Autowired
    private UbigeoService ubigeoService;

    @ExceptionHandler(Exception.class)   ///objeniendo el error de excepcion en forma generica
    private ErrorEntity capturadorErrores(Exception ex){
        //capturando la excepcion - enviar el status en conflicto 409
        ErrorEntity error = new ErrorEntity(HttpStatus.CONFLICT.toString(), "Problema interno :)", "A ocurrido un error: "+ex.getMessage());
        return  error;
    }

    @GetMapping
    public ResponseEntity<List<UbigeoDTO>> ListarUbigeo(){
        return ResponseEntity.status(HttpStatus.OK)                       
                            .body(ubigeoService.listarUbigeo());
    }
}
