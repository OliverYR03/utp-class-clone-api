package com.zenkai.pe.UniversidadPOO.service;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Profesor;
import com.zenkai.pe.UniversidadPOO.Entity.Usuario;
import com.zenkai.pe.UniversidadPOO.dto.AlumnoDTO;
import com.zenkai.pe.UniversidadPOO.dto.ProfesorDTO;
import com.zenkai.pe.UniversidadPOO.enums.EstadoUsuario;
import com.zenkai.pe.UniversidadPOO.enums.RolUsuario;
import com.zenkai.pe.UniversidadPOO.repository.IAlumnoRepository;
import com.zenkai.pe.UniversidadPOO.repository.IProfesorRepository;
import com.zenkai.pe.UniversidadPOO.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdministradorService {

    @Autowired
    private IAlumnoRepository ialumnoRepository;

    @Autowired
    private IProfesorRepository iprofesorRepository;
    
    @Autowired
    private IUsuarioRepository iusuarioRepository;

    /* ALUMNO */
    public Alumno actualizarAlumno(String codigo, AlumnoDTO datos) {
        Alumno alumno = ialumnoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Alumno no encontrado"
                ));

        if (datos.getNombres() != null) {
            alumno.setNombres(datos.getNombres());
        }

        if (datos.getApellidos() != null) {
            alumno.setApellidos(datos.getApellidos());
        }

        if (datos.getDni() != null) {
            alumno.setDni(datos.getDni());
        }

        if (datos.getNumero() != null) {
            alumno.setNumero(datos.getNumero());
        }

        if (datos.getCorreo() != null) {
            alumno.setCorreo(datos.getCorreo());
        }

        if (datos.getEstado() != null) {
            alumno.setEstado(datos.getEstado());
        }

        if (datos.getCumpleanos() != null) {
            alumno.setCumpleanos(datos.getCumpleanos());
        }

        if (datos.getSexo() != null) {
            alumno.setSexo(datos.getSexo());
        }

        if (datos.getCiclo() != null) {
            alumno.setCiclo(datos.getCiclo());
        }

        if (datos.getCampus() != null) {
            alumno.setCampus(datos.getCampus());
        }

        if (datos.getModalidad() != null) {
            alumno.setModalidad(datos.getModalidad());
        }

        if (datos.getCarrera() != null) {
            alumno.setCarrera(datos.getCarrera());
        }

        return ialumnoRepository.save(alumno);
    }

    public Alumno crearAlumno(AlumnoDTO datos) {
        if (ialumnoRepository.findByCodigo(datos.getCodigo()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un alumno con ese código"
            );
        }

        Alumno alumno = new Alumno();

        alumno.setCodigo(datos.getCodigo());
        alumno.setNombres(datos.getNombres());
        alumno.setApellidos(datos.getApellidos());
        alumno.setDni(datos.getDni());
        alumno.setNumero(datos.getNumero());
        alumno.setCorreo(datos.getCorreo());
        alumno.setCumpleanos(datos.getCumpleanos());

        if (datos.getSexo() != null) {
            alumno.setSexo(datos.getSexo());
        }

        if (datos.getCiclo() != null) {
            alumno.setCiclo(datos.getCiclo());
        }

        alumno.setCampus(datos.getCampus());
        alumno.setModalidad(datos.getModalidad());
        alumno.setCarrera(datos.getCarrera());

        alumno.setRol(RolUsuario.ESTUDIANTE);
        alumno.setEstado(EstadoUsuario.ACTIVO);

        return ialumnoRepository.save(alumno);
    }

    /* PROFESOR*/
    public Profesor actualizarProfesor(String codigo, ProfesorDTO datos) {
        Profesor profesor = iprofesorRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Profesor no encontrado"
                ));

        // Atributos heredados de Usuario
        if (datos.getNombres() != null) {
            profesor.setNombres(datos.getNombres());
        }

        if (datos.getApellidos() != null) {
            profesor.setApellidos(datos.getApellidos());
        }

        if (datos.getDni() != null) {
            profesor.setDni(datos.getDni());
        }

        if (datos.getNumero() != null) {
            profesor.setNumero(datos.getNumero());
        }

        if (datos.getCorreo() != null) {
            profesor.setCorreo(datos.getCorreo());
        }

        if (datos.getEstado() != null) {
            profesor.setEstado(datos.getEstado());
        }

        if (datos.getSexo() != null) {
            profesor.setSexo(datos.getSexo());
        }

        if (datos.getCumpleanos() != null) {
            profesor.setCumpleanos(datos.getCumpleanos());
        }

        // Atributos heredados de Empleado
        if (datos.getSueldo() != null) {
            profesor.setSueldo(datos.getSueldo());
        }

        if (datos.getHoras() != null) {
            profesor.setHoras(datos.getHoras());
        }

        if (datos.getBono() != null) {
            profesor.setBono(datos.getBono());
        }

        if (datos.getBonoCursos() != null) {
            profesor.setBonoCursos(datos.getBonoCursos());
        }

        return iprofesorRepository.save(profesor);
    }

    public Profesor crearProfesor(ProfesorDTO datos) {
        if (iprofesorRepository.findByCodigo(datos.getCodigo()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un profesor con ese código"
            );
        }

        Profesor profesor = new Profesor();

        profesor.setCodigo(datos.getCodigo());
        profesor.setNombres(datos.getNombres());
        profesor.setApellidos(datos.getApellidos());
        profesor.setDni(datos.getDni());
        profesor.setNumero(datos.getNumero());
        profesor.setCorreo(datos.getCorreo());
        profesor.setCumpleanos(datos.getCumpleanos());
        profesor.setSexo(datos.getSexo());
        profesor.setRol(RolUsuario.PROFESOR);
        profesor.setEstado(EstadoUsuario.ACTIVO);
        profesor.setSueldo(datos.getSueldo());
        profesor.setBono(datos.getBono());
        profesor.setHoras(datos.getHoras());
        profesor.setBonoCursos(datos.getBonoCursos());

        return iprofesorRepository.save(profesor);
    }
    
    
    public void eliminarUsuario(String codigo) {
       Usuario usuario = iusuarioRepository.findByCodigo(codigo);

       if (usuario == null) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND,
                   "Usuario no encontrado"
           );
       }

      iusuarioRepository.delete(usuario);
   }
}
