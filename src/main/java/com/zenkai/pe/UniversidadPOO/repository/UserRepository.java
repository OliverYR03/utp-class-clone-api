package com.zenkai.pe.UniversidadPOO.repository;

import com.zenkai.pe.UniversidadPOO.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Usuario, Long>{
    
    Usuario findByCodigo(String codigo);
}
