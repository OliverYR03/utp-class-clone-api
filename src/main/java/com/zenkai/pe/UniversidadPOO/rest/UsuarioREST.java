package com.zenkai.pe.UniversidadPOO.rest;

import com.zenkai.pe.UniversidadPOO.dto.UsuarioDTO;
import com.zenkai.pe.UniversidadPOO.service.UsuarioService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioREST {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public Set<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/perfil")
    public UsuarioDTO getPerfil(Authentication authentication) {
        return usuarioService.getPerfil(authentication);
    }
}
