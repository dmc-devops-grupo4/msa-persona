package edu.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private Integer idPersona;
    private TipoDocIdentidadDTO tipoDocIdentidad;
    private TipoPersonaDTO tipoPersona;
    private UbigeoDTO ubigeo;
    private String nroDocumento;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String correo;
    private String celular;
    private String ruc;
    private String razonSocial;
    private Boolean activo;
}
