package com.zenkai.pe.UniversidadPOO.repository;

import com.zenkai.pe.UniversidadPOO.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfesorRepository extends JpaRepository<Profesor, Long>{
    
}
