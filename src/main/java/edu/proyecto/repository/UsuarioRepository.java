package edu.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Integer>{
    
    @Query(value = "SELECT * FROM usuario WHERE id_persona=:idPersona AND activo=1 LIMIT 1", nativeQuery = true)
    public UsuarioEntity buscarUsuarioPorIdPersona(@Param("idPersona") Integer idPersona);

}
