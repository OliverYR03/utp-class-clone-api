package com.zenkai.pe.UniversidadPOO.repository;

import com.zenkai.pe.UniversidadPOO.Entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);
    
    @Query("""
    SELECT m FROM Usuario m
    WHERE m.codigo = :codigo
    """)
    Optional<Usuario> findUsuarioByCodigo(
            @Param("codigo") String codigo,
            Sort sort
    );
}