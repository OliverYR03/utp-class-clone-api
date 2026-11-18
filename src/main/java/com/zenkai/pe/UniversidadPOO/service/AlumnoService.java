package com.zenkai.pe.UniversidadPOO.service;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Matricula;
import com.zenkai.pe.UniversidadPOO.dto.UsuarioDTO;
import com.zenkai.pe.UniversidadPOO.enums.EstadoMatricula;
import com.zenkai.pe.UniversidadPOO.repository.IAlumnoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    @Autowired
    private IAlumnoRepository ialumnoRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<Matricula> findMatriculas(
            int idAlumno,
            EstadoMatricula estado,
            Sort sort) {

        return ialumnoRepository.findMatriculas(
                idAlumno,
                estado,
                sort
        );
    }

    public List<Alumno> findAll() {
        return ialumnoRepository.findAll();
    }

    public <S extends Alumno> S save(S entity) {
        return ialumnoRepository.save(entity);
    }

    public Optional<Alumno> findByCodigo(String codigo) {
        return ialumnoRepository.findByCodigo(codigo);
    }

    public List<UsuarioDTO> listarAlumnos() {
        return ialumnoRepository.findAll()
                .stream()
                .map(usuarioMapper::convertirADTO)
                .toList();
    }
}