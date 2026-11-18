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
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    private UsuarioMapper usuarioMapper;
    
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            IUsuarioRepository usuarioRepository,
            AlumnoService alumnoService,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.alumnoService = alumnoService;
        this.passwordEncoder = passwordEncoder;
    }

    
    public Set<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::convertirADTO)
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
        return usuarioMapper.convertirADTO(usuario);
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
