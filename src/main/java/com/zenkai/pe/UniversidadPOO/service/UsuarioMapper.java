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
    return convertirProfesorADTO(profesor);} else if (usuario instanceof Administrador administrador) {
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
    public ProfesorDTO convertirProfesorADTO(Profesor profesor) {
    ProfesorDTO dto = new ProfesorDTO();

    copiarDatosUsuario(profesor, dto);
    dto.setBonoCursos(profesor.getBonoCursos());
    dto.setCursos(profesor.getCursos());
    dto.setSueldo(profesor.getSueldo());
    dto.setBono(profesor.getBono());
    dto.setHoras(profesor.getHoras());

    return dto;
}

private void copiarDatosUsuario(Usuario usuario, UsuarioDTO dto) {
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
}
}
