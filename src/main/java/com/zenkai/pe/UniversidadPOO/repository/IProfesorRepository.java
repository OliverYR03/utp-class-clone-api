package com.zenkai.pe.UniversidadPOO.repository;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Profesor;
import com.zenkai.pe.UniversidadPOO.Entity.Seccion;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProfesorRepository extends JpaRepository<Profesor, Long>{
    
    @Query("""
            SELECT s FROM Seccion s
           WHERE s.profesor.id_usuario= :idProfesor
          
           """)
    Set<Seccion> findSecciones(
            @Param("idProfesor") int idProfesor
    );
    
    @Query("""
        SELECT m.alumno
        FROM Matricula m
        WHERE m.seccion.id_seccion = :idSeccion
          AND m.seccion.profesor.id_usuario = :idProfesor
        """)
    Set<Alumno> findAlumnosBySeccion(
        @Param("idSeccion") int idSeccion,
        @Param("idProfesor") int idProfesor
    );
            
    Optional<Profesor>findByCodigo(@Param("codigo")String codigo);
}
