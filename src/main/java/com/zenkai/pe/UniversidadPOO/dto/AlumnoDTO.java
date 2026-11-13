package com.zenkai.pe.UniversidadPOO.dto;

import com.zenkai.pe.UniversidadPOO.enums.CarreraAlumno;
import com.zenkai.pe.UniversidadPOO.enums.ModalidadAlumno;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

public class AlumnoDTO extends UsuarioDTO {

    private int ciclo;
    private String campus;

    @Enumerated(EnumType.STRING)
    private ModalidadAlumno modalidad;
    @Enumerated(EnumType.STRING)
    private CarreraAlumno carrera;

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
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
