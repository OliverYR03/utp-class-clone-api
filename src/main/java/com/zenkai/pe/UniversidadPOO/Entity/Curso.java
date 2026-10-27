package com.zenkai.pe.UniversidadPOO.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_curso;
    private String nombre, descripcion;
    private int creditos, horas, ciclo;
    
    @ManyToMany
    @JoinTable(
            name = "curso_profesor",
            joinColumns = @JoinColumn(name = "id_curso"),
            inverseJoinColumns = @JoinColumn(name= "id_profesor")
    )
    private List<Profesor> profesor;

    public Curso(){}
    
    public Curso(String nombre, String descripcion, int creditos, int horas, int ciclo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.horas = horas;
        this.ciclo = ciclo;
    }
    
    
}
 