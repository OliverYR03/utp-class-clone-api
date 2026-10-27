package com.zenkai.pe.UniversidadPOO.Entity;

import com.zenkai.pe.UniversidadPOO.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "alumno")
@PrimaryKeyJoinColumn(name = "id_alumno")
public class Alumno extends Usuario {

    private int ciclo;
    private String campus;

    @Enumerated(EnumType.STRING)
    private ModalidadAlumno modalidad;

    @Enumerated(EnumType.STRING)
    private CarreraAlumno carrera;

    public Alumno() {
            }

   

    public int getCiclo() {
        return ciclo;
    }

    public String getCampus() {
        return campus;
    }

    public Alumno(int ciclo, String campus, ModalidadAlumno modalidad, CarreraAlumno carrera, int id_usuario, String codigo, String nombres, String apellidos, String dni, String numero, String correo, RolUsuario rol, String contrasena, char sexo, Date cumpleanos) {
        super(id_usuario, codigo, nombres, apellidos, dni, numero, correo, RolUsuario.ESTUDIANTE, contrasena, sexo, cumpleanos);
        this.ciclo = ciclo;
        this.campus = campus;
        this.modalidad = modalidad;
        this.carrera = carrera;
    }

    public ModalidadAlumno getModalidad() {
        return modalidad;
    }

    public CarreraAlumno getCarrera() {
        return carrera;
    }

    
}
