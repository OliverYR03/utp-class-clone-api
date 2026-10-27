package com.zenkai.pe.UniversidadPOO.repository;

import com.zenkai.pe.UniversidadPOO.Entity.Matricula;
import com.zenkai.pe.UniversidadPOO.enums.EstadoMatricula;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMatriculaRepository extends JpaRepository<Matricula, Long> {

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
}
