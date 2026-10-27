package com.zenkai.pe.UniversidadPOO.service;

import com.zenkai.pe.UniversidadPOO.Entity.SecurityUser;
import com.zenkai.pe.UniversidadPOO.Entity.Usuario;
import com.zenkai.pe.UniversidadPOO.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String codigo)
            throws UsernameNotFoundException {

        Usuario usuario = userRepository.findByCodigo(codigo);

        if (usuario == null) {
            throw new UsernameNotFoundException("Alumno no encontrado");
        }

        return new SecurityUser(usuario);
    }
}