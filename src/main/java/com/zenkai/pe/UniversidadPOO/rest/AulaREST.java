package com.zenkai.pe.UniversidadPOO.rest;

import com.zenkai.pe.UniversidadPOO.Entity.Aula;
import com.zenkai.pe.UniversidadPOO.service.AulaService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aula")
public class AulaREST {

    @Autowired
    private AulaService aulaService;

    @GetMapping
    public ResponseEntity<List<Aula>> getAllAulas() {
        return ResponseEntity.ok(aulaService.findAll());
    }
    
    @GetMapping("/disponibles")
    public ResponseEntity<List<Aula>> getAulasActivasOrdenadas() {
        Sort orden = Sort.by(Sort.Direction.ASC, "codigo"); 

        return ResponseEntity.ok(aulaService.findByEsDisponibleTrue(orden));
    }
    
    @GetMapping("/nodisponibles")
    public ResponseEntity<List<Aula>> getAulasNoActivasOrdenadas() {
        Sort orden = Sort.by(Sort.Direction.ASC, "codigo"); 

        return ResponseEntity.ok(aulaService.findByEsDisponibleFalse(orden));
    }

    
   @GetMapping("/{id}")
    public ResponseEntity<Optional<Aula>> getAulaById(@PathVariable("id") Long idAula) {
        return ResponseEntity.ok(aulaService.findById(idAula));
    }
    
    @PostMapping("/new")
    private ResponseEntity<Aula> saveAula (@RequestBody Aula aula){
        try {
            Aula aulaGuardada = aulaService.save(aula);
            return ResponseEntity.created(new URI("/aula/"+aulaGuardada.getId_aula())).body(aulaGuardada);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
    }
    
    

}
