package com.zenkai.pe.UniversidadPOO.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="seccion")
@Data
public class Seccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_seccion;
    private String numero, turno, periodo;
    
    @OneToOne
    @JoinColumn(name="id_curso")
    private Curso curso;
    
    @ManyToOne
    @JoinColumn(name="id_aula")
    private Aula aula;
    
    @ManyToOne
    @JoinColumn(name="id_profesor")
    private Profesor profesor;

    public Seccion(){}
    
    public Seccion(String numero, Curso curso, Aula aula, Profesor profesor, String turno, String periodo) {
        this.numero = numero;
        this.curso = curso;
        this.aula = aula;
        this.profesor = profesor;
        this.turno = turno;
        this.periodo = periodo;
    }
    
    
}
