package com.zenkai.pe.UniversidadPOO.rest;

import com.zenkai.pe.UniversidadPOO.Entity.Matricula;
import com.zenkai.pe.UniversidadPOO.enums.EstadoMatricula;
import com.zenkai.pe.UniversidadPOO.service.MatriculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matricula")
public class MatriculaREST {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<Matricula>> getAllMatriculas() {
        return ResponseEntity.ok(matriculaService.findAll());
    }

    @GetMapping("/encurso")
    public ResponseEntity<List<Matricula>> findMatriculas(
            @RequestParam(required = false) Integer idAlumno) {

        if (idAlumno == null) {
            return ResponseEntity.badRequest().build();
        }

        Sort sort = Sort.by("id_matricula");

        return ResponseEntity.ok(
                matriculaService.findMatriculas(idAlumno, EstadoMatricula.EN_CURSO,sort)
        );
    }
}
