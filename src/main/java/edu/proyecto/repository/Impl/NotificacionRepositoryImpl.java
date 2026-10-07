package edu.proyecto.repository.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import edu.proyecto.dto.EmailRegistroPersonaDTO;
import edu.proyecto.repository.NotificacionRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Si api-notificaciones no responde solo se registra el error.
 */
@Slf4j
@Repository
public class NotificacionRepositoryImpl implements NotificacionRepository {
    private RestTemplate restTemplate;

    @Value("${uri.service.notificaciones}")
    private String urlApiNotificaciones;

    public NotificacionRepositoryImpl() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);
        restTemplate = new RestTemplate(factory);
    }

    @Override
    public void notificarRegistroPersona(EmailRegistroPersonaDTO data) {
        try {
            restTemplate.postForEntity(urlApiNotificaciones + "/api/v1/eventos/registro-persona", data, Void.class);
        } catch (Exception e) {
            log.error("No se pudo notificar el evento registro-persona: {}", e.getMessage());
        }
    }
}
