package com.zenkai.pe.UniversidadPOO.service;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Matricula;
import com.zenkai.pe.UniversidadPOO.Utils.Comparador;
import com.zenkai.pe.UniversidadPOO.dto.UsuarioDTO;
import com.zenkai.pe.UniversidadPOO.enums.EstadoMatricula;
import com.zenkai.pe.UniversidadPOO.repository.IAlumnoRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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
    
   public List<UsuarioDTO> ordenarAlumnos(
        String ordenarPor,
        boolean ascendente
) {
    List<UsuarioDTO> resultado = new ArrayList<>(listarAlumnos());

    Function<UsuarioDTO, String> obtenerValor = switch (ordenarPor) {
        case "nombres" -> UsuarioDTO::getNombres;
        case "apellidos" -> UsuarioDTO::getApellidos;
        case "codigo" -> UsuarioDTO::getCodigo;
        case "dni" -> UsuarioDTO::getDni;
        default -> throw new IllegalArgumentException(
                "Criterio inválido: " + ordenarPor
        );
    };

    resultado.sort(new Comparador<>(
            obtenerValor,
            String.CASE_INSENSITIVE_ORDER,
            ascendente
    ));

    return resultado;
}
}