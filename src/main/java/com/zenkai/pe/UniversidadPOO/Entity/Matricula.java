package com.zenkai.pe.UniversidadPOO.Entity;

import com.zenkai.pe.UniversidadPOO.enums.EstadoMatricula;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="matricula")
@Data
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_matricula;
    @OneToOne
    @JoinColumn(name="id_alumno")
    private Alumno alumno;
    @OneToOne
    @JoinColumn(name="id_seccion")
    private Seccion seccion;
    @Enumerated(EnumType.STRING)
    private EstadoMatricula estado;
    private double nota;
    private String periodo;

    public Matricula(){}
    
    public Matricula(Alumno alumno,Seccion seccion, EstadoMatricula estado, double nota, String periodo) {
        this.alumno = alumno;
        this.seccion = seccion;
        this.estado = estado;
        this.nota = nota;
        this.periodo = periodo;
    }
    
    
    
}
