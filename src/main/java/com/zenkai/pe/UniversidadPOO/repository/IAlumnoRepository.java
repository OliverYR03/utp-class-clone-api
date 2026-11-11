package com.zenkai.pe.UniversidadPOO.repository;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Matricula;
import com.zenkai.pe.UniversidadPOO.Entity.Seccion;
import com.zenkai.pe.UniversidadPOO.enums.EstadoMatricula;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAlumnoRepository extends JpaRepository<Alumno, Integer> {

    @Query("""
    SELECT m FROM Matricula m
    WHERE m.alumno.id_usuario = :idAlumno
    AND m.estado = :estado
    """)
    List<Matricula> findMatriculas(
            @Param("idAlumno") int idAlumno,
            @Param("estado") EstadoMatricula estado,
            Sort sort
    );

   /* @Query("""
    SELECT m FROM Matricula m
    WHERE m.alumno.id_usuario = :idAlumno
    AND m.estado = :estado
    """)
    List<Seccion> findSecciones(@Param("idAlumno") int idAlumno);*/

    Optional<Alumno> findByCodigo(@Param("codigo") String codigo);

}
