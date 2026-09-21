package edu.proyecto.service;

import java.util.List;

import edu.proyecto.dto.PersonaDTO;
import edu.proyecto.dto.EditarPersonaRequestDTO;
import edu.proyecto.dto.GuardarPersonaRequestDTO;

public interface PersonaService {
    public List<PersonaDTO> listarPersonas();
    public PersonaDTO obtenerPersona(Integer idPersona);
    public PersonaDTO buscarPersona(Integer idTipoPersona, Integer idTipoDocIdentidad, String nroDocumento);
    public PersonaDTO grabarPersona(GuardarPersonaRequestDTO persona);
    public PersonaDTO actualizarPersona(Integer idPersona, EditarPersonaRequestDTO persona);
    public void eliminarPersona(Integer idPersona);
}
