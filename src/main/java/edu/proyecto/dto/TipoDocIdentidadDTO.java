package edu.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocIdentidadDTO {
    private Integer idTipoDocIdentidad; 
    private String descripcion;
}
