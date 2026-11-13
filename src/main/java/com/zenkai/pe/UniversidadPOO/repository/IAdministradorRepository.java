package com.zenkai.pe.UniversidadPOO.repository;

import com.zenkai.pe.UniversidadPOO.Entity.Administrador;
import com.zenkai.pe.UniversidadPOO.dto.UsuarioDTO;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IAdministradorRepository extends JpaRepository<Administrador, Integer> {
    
   /*  @Query("""
           SELECT a FROM usuarios INNER JOIN 
           """)
     Set<UsuarioDTO> findAllUsuarios();
     */
     }
