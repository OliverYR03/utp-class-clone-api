package com.zenkai.pe.UniversidadPOO.service;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Profesor;
import com.zenkai.pe.UniversidadPOO.Entity.Seccion;
import com.zenkai.pe.UniversidadPOO.dto.ProfesorDTO;
import com.zenkai.pe.UniversidadPOO.dto.UsuarioDTO;
import com.zenkai.pe.UniversidadPOO.repository.IProfesorRepository;
import com.zenkai.pe.UniversidadPOO.repository.IUsuarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService {

    @Autowired
    public IProfesorRepository iprofesorRepository;

    @Autowired
    public UsuarioMapper usuarioMapper;
    
    public List<Profesor> findAll() {
        return iprofesorRepository.findAll();
    }

    public Set<Seccion> findSecciones(int idProfesor) {
        return iprofesorRepository.findSecciones(idProfesor);
    }

    public Optional<Profesor> findByCodigo(String codigo) {
        return iprofesorRepository.findByCodigo(codigo);
    }

    public Set<Alumno> findAlumnosBySeccion(int idSeccion, int idProfesor) {
        return iprofesorRepository.findAlumnosBySeccion(idSeccion, idProfesor)    ;
    }
    
    public List<UsuarioDTO> listarProfesores() {
    return iprofesorRepository.findAll()
            .stream()
            .map(usuarioMapper::convertirADTO)
            .toList();
}

    public void deleteAllInBatch(Iterable<Profesor> entities) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Profesor getById(Integer id) {
        return iprofesorRepository.getById(id);
    }

    public <S extends Profesor> S save(S entity) {
        return iprofesorRepository.save(entity);
    }

}
