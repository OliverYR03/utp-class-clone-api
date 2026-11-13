package com.zenkai.pe.UniversidadPOO.service;

import com.zenkai.pe.UniversidadPOO.Entity.Administrador;
import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Profesor;
import com.zenkai.pe.UniversidadPOO.Entity.Usuario;
import com.zenkai.pe.UniversidadPOO.dto.AdministradorDTO;
import com.zenkai.pe.UniversidadPOO.dto.AlumnoDTO;
import com.zenkai.pe.UniversidadPOO.dto.ProfesorDTO;
import com.zenkai.pe.UniversidadPOO.dto.UsuarioDTO;
import com.zenkai.pe.UniversidadPOO.enums.EstadoUsuario;
import com.zenkai.pe.UniversidadPOO.repository.IUsuarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final AlumnoService alumnoService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            IUsuarioRepository usuarioRepository,
            AlumnoService alumnoService,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.alumnoService = alumnoService;
        this.passwordEncoder = passwordEncoder;
    }

    private UsuarioDTO convertirADTO(Usuario usuario) {
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

        // Datos comunes heredados de Usuario.
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

    public Set<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toSet());
    }

    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public Optional<Usuario> findUsuarioByCodigo(String codigo) {
        return usuarioRepository.findUsuarioByCodigo(codigo);
    }

    public Usuario findByCodigo(String codigo) {
        return usuarioRepository.findByCodigo(codigo);
    }

    public UsuarioDTO getPerfil(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Sesión no autenticada"
            );
        }

        Usuario usuario = usuarioRepository
                .findUsuarioByCodigo(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Usuario no encontrado"
                ));
        System.out.println("Clase: " + usuario.getClass().getName());
        System.out.println(
                "Es administrador: " + (usuario instanceof Administrador)
        );
        return convertirADTO(usuario);
    }

    public UserDetails loadUserByUsername(String codigo) {
        Usuario usuario = usuarioRepository.findUsuarioByCodigo(codigo)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado con el codigo: " + codigo
                ));

        return User.withUsername(usuario.getCodigo())
                .password(usuario.getPasswordHash())
                .roles(usuario.getRol().name())
                .disabled(usuario.getEstado() != EstadoUsuario.ACTIVO)
                .build();
    }

    public Alumno getAlumnoData(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Sesión no autenticada"
            );
        }

        return alumnoService.findByCodigo(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Alumno no encontrado"
                ));
    }

    public boolean authenticate(String codigo, String password) {
        Usuario usuario = usuarioRepository.findUsuarioByCodigo(codigo)
                .orElseThrow(()
                        -> new BadCredentialsException("Credenciales incorrectas")
                );

        String hash = usuario.getPasswordHash();

        if (usuario.getEstado() != EstadoUsuario.ACTIVO
                || password == null
                || hash == null
                || hash.isBlank()
                || !passwordEncoder.matches(password, hash)) {

            throw new BadCredentialsException("Credenciales incorrectas");
        }

        return true;
    }
}
