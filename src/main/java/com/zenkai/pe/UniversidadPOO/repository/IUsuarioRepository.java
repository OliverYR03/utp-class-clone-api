package com.zenkai.pe.UniversidadPOO.repository;

import com.zenkai.pe.UniversidadPOO.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreo(String correo);
    
    Optional<Usuario> findUsuarioByCodigo(String codigo    );
    
    Usuario findByCodigo(String codigo);
}