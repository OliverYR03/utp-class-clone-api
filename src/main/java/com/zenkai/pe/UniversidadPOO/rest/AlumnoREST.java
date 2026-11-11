package com.zenkai.pe.UniversidadPOO.rest;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Matricula;
import com.zenkai.pe.UniversidadPOO.Entity.Usuario;
import com.zenkai.pe.UniversidadPOO.enums.EstadoMatricula;
import com.zenkai.pe.UniversidadPOO.service.AlumnoService;
import com.zenkai.pe.UniversidadPOO.service.UsuarioService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/student")
public class AlumnoREST {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/me")
    public ResponseEntity<Optional<Alumno>> miPerfil(Authentication authentication) {
        String codigo = authentication.getName();      
        return ResponseEntity.ok(alumnoService.findByCodigo(codigo));
    }


    @GetMapping("/encurso")
    public ResponseEntity<List<Matricula>> findMatriculas(
            Authentication authentication
    ) {
        Sort sort = Sort.by("estado");
        
        String codigo = authentication.getName();
        
        Alumno alumno = alumnoService.findByCodigo(codigo)
                .orElseThrow(()
                        -> new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Usuario no encontrado"
                        )
                );

        int idAlumno = alumno.getId_usuario();

        return ResponseEntity.ok(
                alumnoService.findMatriculas(
                        idAlumno,
                        EstadoMatricula.EN_CURSO,
                        sort
                )
        );
    }

    @PostMapping("/new")
    public ResponseEntity<Alumno> saveAlumno(@RequestBody Alumno alumno
    ) {
        try {
            Alumno alumnoSaved = alumnoService.save(alumno);
            return ResponseEntity.created(new URI("/alumno/" + alumnoSaved.getCodigo())).body(alumno);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
