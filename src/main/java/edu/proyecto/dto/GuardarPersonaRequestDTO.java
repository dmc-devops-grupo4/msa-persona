package edu.proyecto.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuardarPersonaRequestDTO {
    private Integer idTipoDocIdentidad;
    private Integer idTipoPersona;
    private String codUbigeo; 
    private String nroDocumento;
    private String nombres;  
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String correo; 
    private String celular;  
    private String ruc; 
    private String razonSocial;
    private String password;
}
