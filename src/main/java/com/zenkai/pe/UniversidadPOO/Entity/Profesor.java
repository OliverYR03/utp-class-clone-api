package com.zenkai.pe.UniversidadPOO.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenkai.pe.UniversidadPOO.enums.RolUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="profesor")
@PrimaryKeyJoinColumn(name = "id_profesor")
public class Profesor extends Empleado {
   
    @Column(name="bonoCursos")
    private double bonoCursos;
    @OneToMany(mappedBy = "profesor")
    @JsonBackReference
    private  List<Curso> cursos;
    
    public Profesor(){}

    public Profesor(double bonoCursos, List<Curso> cursos, BigDecimal sueldo, int horas, BigDecimal bono, int id_usuario, String codigo, String nombres, String apellidos, String dni, String numero, String correo, RolUsuario rol, String contrasena, char sexo, Date cumpleanos) {
        super(sueldo, horas, bono, id_usuario, codigo, nombres, apellidos, dni, numero, correo, RolUsuario.PROFESOR, contrasena, sexo, cumpleanos);
        this.bonoCursos = 30;
        this.cursos = cursos;
    }


    @Override
    public double calcularSueldo() {
        return 2;
    }
    
}
