package com.zenkai.pe.UniversidadPOO.rest;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Profesor;
import com.zenkai.pe.UniversidadPOO.dto.AlumnoDTO;
import com.zenkai.pe.UniversidadPOO.dto.ProfesorDTO;
import com.zenkai.pe.UniversidadPOO.dto.UsuarioDTO;
import com.zenkai.pe.UniversidadPOO.service.AdministradorService;
import com.zenkai.pe.UniversidadPOO.service.AlumnoService;
import com.zenkai.pe.UniversidadPOO.service.ProfesorService;
import com.zenkai.pe.UniversidadPOO.service.UsuarioMapper;
import com.zenkai.pe.UniversidadPOO.service.UsuarioService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/admin")
public class AdministradorREST {

    /* AUTO INYECCIÓN */
    
    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioMapper usuarioMapper;

    /* USUARIOS */
    
    
    
    @GetMapping("/usuarios/{codigo}/info")
    @PreAuthorize("hasRole('ADMIN')")
    public UsuarioDTO listarProfesor(@PathVariable("codigo") String codigo) {
         return usuarioService.findUsuarioByCodigo(codigo)
            .map(usuarioMapper::convertirADTO)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuario no encontrado"
            ));
                
    }
    
    @DeleteMapping("/usuarios/{codigo}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(
            @PathVariable("codigo") String codigo) {

        administradorService.eliminarUsuario(codigo);
    }
    
    /*   PROFESORES CRUD   */
    
    @GetMapping("/usuarios/profesores")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioDTO> listarProfesores() {
        return profesorService.listarProfesores();
    }
    
    @PutMapping("/usuarios/profesores/{codigo}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public UsuarioDTO actualizarProfesor(
            @PathVariable("codigo") String codigo,
            @RequestBody ProfesorDTO datos) {

        Profesor profesor = administradorService.actualizarProfesor(codigo, datos);
        return usuarioMapper.convertirADTO(profesor);
        }
    
    @PostMapping("/usuarios/profesores/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO crearProfesor(@RequestBody ProfesorDTO datos) {
        Profesor profesor = administradorService.crearProfesor(datos);
        return usuarioMapper.convertirADTO(profesor);
    }
    
    /* ALUMNOS CRUD*/

    @GetMapping("/usuarios/alumnos")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioDTO> listarAlumnos() {
        return alumnoService.listarAlumnos();
    }
    
    @PutMapping("/usuarios/alumnos/{codigo}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public UsuarioDTO actualizarAlumno(
            @PathVariable("codigo") String codigo,
            @RequestBody AlumnoDTO datos) {

        Alumno alumno = administradorService.actualizarAlumno(codigo, datos);
        return usuarioMapper.convertirADTO(alumno);
        }
    
    @PostMapping("/usuarios/alumnos/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO crearAlumno(@RequestBody AlumnoDTO datos) {
        Alumno alumno = administradorService.crearAlumno(datos);
        return usuarioMapper.convertirADTO(alumno);
    }

}
