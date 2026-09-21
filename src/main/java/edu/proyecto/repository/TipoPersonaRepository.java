package edu.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.TipoPersonaEntity;

@Repository
public interface TipoPersonaRepository extends JpaRepository<TipoPersonaEntity, Integer> {
    @Query(value = "SELECT * FROM tipo_persona WHERE activo=1", nativeQuery = true)
    public List<TipoPersonaEntity> consultarTipoPersonaActivos();
}
