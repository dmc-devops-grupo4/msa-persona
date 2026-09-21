package edu.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPersonaDTO {
    private Integer idTipoPersona; 
    private String descripcion;
}
