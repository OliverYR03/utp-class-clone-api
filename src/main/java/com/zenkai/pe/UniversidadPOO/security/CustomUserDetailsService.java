package com.zenkai.pe.UniversidadPOO.security;

import com.zenkai.pe.UniversidadPOO.Entity.Usuario;
import com.zenkai.pe.UniversidadPOO.enums.RolUsuario;
import com.zenkai.pe.UniversidadPOO.repository.IUsuarioRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository iusuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
        Usuario usuario = iusuarioRepository.findUsuarioByCodigo(codigo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el codigo: " + codigo));

        return new User(usuario.getCodigo(), usuario.getContrasena(), mapearRoles(usuario));

    }

    private Collection<? extends GrantedAuthority> mapearRoles(Usuario usuario) {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name())
        );
    }
}
