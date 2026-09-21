package edu.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.UbigeoEntity;

@Repository
public interface UbigeoRepository extends JpaRepository<UbigeoEntity,String>{
    @Query(value = "SELECT * FROM ubigeo WHERE activo=1", nativeQuery = true)
    public List<UbigeoEntity> consultarUbigeoActivos();
}
