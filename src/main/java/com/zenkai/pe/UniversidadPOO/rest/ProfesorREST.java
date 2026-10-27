package com.zenkai.pe.UniversidadPOO.rest;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Profesor;
import com.zenkai.pe.UniversidadPOO.service.ProfesorService;
import static java.lang.Thread.State.NEW;
import java.util.List;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorREST {
    
    @Autowired
    public ProfesorService profesorService;
    
    @GetMapping
    public ResponseEntity<List<Profesor>> getAllProfesores(){
        return ResponseEntity.ok(profesorService.findAll());
    }
    
    @PostMapping("/new")
    public ResponseEntity<Profesor> saveProfesor(@RequestBody Profesor profesor){
        try{
            Profesor profesorGuardado = profesorService.save(profesor);
            return ResponseEntity.created(new URI("/profesor/"+profesorGuardado.getCodigo())).body(profesorGuardado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

        @GetMapping("/{id}")
    public ResponseEntity<Profesor> getAlumnoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(profesorService.getById(id));
    }

    
}
