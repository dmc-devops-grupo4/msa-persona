package edu.proyecto.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditarPersonaRequestDTO {
    private String codUbigeo;
    private String direccion;
    private String correo;
    private String celular;
}
