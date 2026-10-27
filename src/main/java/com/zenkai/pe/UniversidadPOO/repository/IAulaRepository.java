package com.zenkai.pe.UniversidadPOO.repository;

import com.zenkai.pe.UniversidadPOO.Entity.Aula;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IAulaRepository extends JpaRepository<Aula,Long> {
    @Query("SELECT a FROM Aula a WHERE a.esDisponible = true")
    List<Aula> findByEsDisponibleTrue(Sort sort);
    
    @Query("SELECT a FROM Aula a WHERE a.esDisponible = false")
    List<Aula> findByEsDisponibleFalse(Sort sort);
}
