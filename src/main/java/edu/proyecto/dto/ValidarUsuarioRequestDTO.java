package edu.proyecto.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidarUsuarioRequestDTO {
    private Integer idTipoPersona;
    private Integer idTipoDocIdentidad;
    private String nroDocumento;
    private String password;
}
