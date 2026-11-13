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
    private String nombre, descripcion, modalidad, nivel;
    private int creditos, horas, ciclo;
    
    @ManyToMany
    @JoinTable(
            name = "curso_profesor",
            joinColumns = @JoinColumn(name = "id_curso"),
            inverseJoinColumns = @JoinColumn(name= "id_profesor")
    )
    private List<Profesor> profesor;

    public Curso(){}
    
    public Curso(String nombre, String descripcion, String modalidad, String nivel, int creditos, int horas, int ciclo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.modalidad = modalidad;
        this.nivel = nivel;
        this.creditos = creditos;
        this.horas = horas;
        this.ciclo = ciclo;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public List<Profesor> getProfesor() {
        return profesor;
    }

    public void setProfesor(List<Profesor> profesor) {
        this.profesor = profesor;
    }
    
    
}
 