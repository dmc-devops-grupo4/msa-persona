package edu.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.TipoDocIdentidadEntity;

@Repository
public interface TipoDocIdentidadRepository extends JpaRepository<TipoDocIdentidadEntity,Integer>{    
    @Query(value = "SELECT * FROM tipo_doc_identidad WHERE activo=1", nativeQuery = true)
    public List<TipoDocIdentidadEntity> consultarTipoDocIdentidadActivos();
}
