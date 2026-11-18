package com.zenkai.pe.UniversidadPOO.dto;

import com.zenkai.pe.UniversidadPOO.enums.CarreraAlumno;
import com.zenkai.pe.UniversidadPOO.enums.EstadoUsuario;
import com.zenkai.pe.UniversidadPOO.enums.ModalidadAlumno;
import com.zenkai.pe.UniversidadPOO.enums.RolUsuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AlumnoDTO extends UsuarioDTO {

    private Integer ciclo;
    private String campus;
    private ModalidadAlumno modalidad;
    private CarreraAlumno carrera;

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public ModalidadAlumno getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadAlumno modalidad) {
        this.modalidad = modalidad;
    }

    public CarreraAlumno getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraAlumno carrera) {
        this.carrera = carrera;
    }
    
    
}