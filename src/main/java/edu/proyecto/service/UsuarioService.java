package edu.proyecto.service;

import edu.proyecto.dto.UsuarioDTO;
import edu.proyecto.dto.ValidarUsuarioRequestDTO;

public interface UsuarioService {
     public UsuarioDTO validarUsuario(Integer idTipoPersona, Integer idTipoDocIdentidad, String nroDocumento, String password);
}