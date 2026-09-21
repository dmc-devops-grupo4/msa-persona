package edu.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.PersonaEntity;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer>{
    @Query(value = "SELECT * FROM persona WHERE activo=1", nativeQuery = true)
    public List<PersonaEntity> listarPersonasActivas();

    public PersonaEntity findFirstByCorreoAndActivoTrue(String correo);

    @Query(value = "SELECT * FROM persona WHERE id_tipo_persona=:idTipoPersona AND id_tipo_doc_identidad=:idTipoDocIdentidad AND nro_documento=:nroDocumento AND activo=1 LIMIT 1", nativeQuery = true)
    public PersonaEntity buscarPersona(@Param("idTipoPersona") Integer idTipoPersona, @Param("idTipoDocIdentidad") Integer idTipoDocIdentidad, @Param("nroDocumento") String nroDocumento);
}
