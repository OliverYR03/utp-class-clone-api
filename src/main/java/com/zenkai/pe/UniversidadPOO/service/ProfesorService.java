package com.zenkai.pe.UniversidadPOO.service;

import com.zenkai.pe.UniversidadPOO.Entity.Alumno;
import com.zenkai.pe.UniversidadPOO.Entity.Profesor;
import com.zenkai.pe.UniversidadPOO.Entity.Seccion;
import com.zenkai.pe.UniversidadPOO.Utils.Comparador;
import com.zenkai.pe.UniversidadPOO.Utils.IteradorFiltroUsuarios;
import com.zenkai.pe.UniversidadPOO.dto.ProfesorDTO;
import com.zenkai.pe.UniversidadPOO.repository.IProfesorRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public List<ProfesorDTO> listarProfesores() {
    return iprofesorRepository.findAll()
            .stream()
            .map(usuarioMapper::convertirProfesorADTO)
            .toList();
}

    
public List<ProfesorDTO> ordenarProfesores(
        String ordenarPor, boolean ascendente, String texto
) {
    List<ProfesorDTO> resultado =
            IteradorFiltroUsuarios.filtrarPorTexto(listarProfesores(), texto);

        Comparator<ProfesorDTO> comparador = switch (ordenarPor) {
            case "nombres" -> new Comparador<>(
                    ProfesorDTO::getNombres, String.CASE_INSENSITIVE_ORDER, ascendente);
            case "apellidos" -> new Comparador<>(
                    ProfesorDTO::getApellidos, String.CASE_INSENSITIVE_ORDER, ascendente);
            case "codigo" -> new Comparador<>(
                    ProfesorDTO::getCodigo, String.CASE_INSENSITIVE_ORDER, ascendente);
            case "sueldo" -> new Comparador<>(
                    ProfesorDTO::getSueldo, Comparator.naturalOrder(), ascendente);
            default -> throw new IllegalArgumentException(
                    "Criterio inválido: " + ordenarPor);
        };

        resultado.sort(comparador);
        return resultado;
    }

    /* genericos */
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
