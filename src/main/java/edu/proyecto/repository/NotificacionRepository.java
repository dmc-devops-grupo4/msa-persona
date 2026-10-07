package edu.proyecto.repository;

import edu.proyecto.dto.EmailRegistroPersonaDTO;

public interface NotificacionRepository {
    public void notificarRegistroPersona(EmailRegistroPersonaDTO data);
}
