package edu.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbigeoDTO {
    private String codUbigeo;
    private String departamento;
    private String provincia;
    private String distrito;
}
