package com.zenkai.pe.UniversidadPOO.service;

import com.zenkai.pe.UniversidadPOO.Entity.Administrador;
import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Profesor;
import com.zenkai.pe.UniversidadPOO.Entity.Usuario;
import com.zenkai.pe.UniversidadPOO.dto.AdministradorDTO;
import com.zenkai.pe.UniversidadPOO.dto.AlumnoDTO;
import com.zenkai.pe.UniversidadPOO.dto.ProfesorDTO;
import com.zenkai.pe.UniversidadPOO.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
   
    public UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO dto;

        if (usuario instanceof Alumno alumno) {
            AlumnoDTO alumnoDTO = new AlumnoDTO();

            alumnoDTO.setCiclo(alumno.getCiclo());
            alumnoDTO.setCampus(alumno.getCampus());
            alumnoDTO.setModalidad(alumno.getModalidad());
            alumnoDTO.setCarrera(alumno.getCarrera());

            dto = alumnoDTO;

        } else if (usuario instanceof Profesor profesor) {
            ProfesorDTO profesorDTO = new ProfesorDTO();

            profesorDTO.setBonoCursos(profesor.getBonoCursos());
            profesorDTO.setCursos(profesor.getCursos());
            profesorDTO.setSueldo(profesor.getSueldo());
            profesorDTO.setBono(profesor.getBono());
            profesorDTO.setHoras(profesor.getHoras());
            dto = profesorDTO;

        } else if (usuario instanceof Administrador administrador) {
            AdministradorDTO administradorDTO = new AdministradorDTO();

            administradorDTO.setBonoGestion(
                    administrador.getBonoGestion()
            );
            administradorDTO.setSueldo(administrador.getSueldo());
            administradorDTO.setBono(administrador.getBono());
            administradorDTO.setHoras(administrador.getHoras());
            dto = administradorDTO;

        } else {
            dto = new UsuarioDTO();
        }

        dto.setCodigo(usuario.getCodigo());
        dto.setNombres(usuario.getNombres());
        dto.setApellidos(usuario.getApellidos());
        dto.setDni(usuario.getDni());
        dto.setNumero(usuario.getNumero());
        dto.setCorreo(usuario.getCorreo());
        dto.setEstado(usuario.getEstado());
        dto.setRol(usuario.getRol());
        dto.setSexo(usuario.getSexo());
        dto.setCumpleanos(usuario.getCumpleanos());

        return dto;
    }

}
