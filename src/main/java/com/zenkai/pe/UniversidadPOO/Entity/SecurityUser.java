package com.zenkai.pe.UniversidadPOO.Entity;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails{
    
    private Usuario usuario;

    public SecurityUser(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    System.out.println("Es un: " + usuario.getRol());

    return Collections.singletonList(
        new SimpleGrantedAuthority("ROLE_" + usuario.getRol())
    );
}

    @Override
    public String getPassword() {
       return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getCodigo();
    }
    
}
