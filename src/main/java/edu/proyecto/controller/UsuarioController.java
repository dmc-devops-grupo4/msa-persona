package edu.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.UsuarioDTO;
import edu.proyecto.entity.ErrorEntity;
import edu.proyecto.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/usuarios")
@Tag(name = "Usuario", description = "Api Usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/por-credenciales")
    public ResponseEntity<UsuarioDTO> validarUsuario(
        @RequestParam(required = true) Integer idTipoPersona, 
        @RequestParam(required = true) Integer idTipoDocIdentidad, 
        @RequestParam(required = true) String nroDocumento,
        @RequestParam(required = true) String password
    ) {
        UsuarioDTO usuario = usuarioService.validarUsuario(idTipoPersona, idTipoDocIdentidad, nroDocumento, password);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @ExceptionHandler(Exception.class)   ///obteniendo el error de excepcion en forma generica
    private ErrorEntity capturadorErrores(Exception ex){
        //capturando la excepcion - enviar el status en conflicto 409
        ErrorEntity error = new ErrorEntity(HttpStatus.CONFLICT.toString(), "Problema interno :)", "A ocurrido un error: "+ex.getMessage());
        return  error;
    }

}
