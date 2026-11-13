package com.zenkai.pe.UniversidadPOO.Entity;

import com.zenkai.pe.UniversidadPOO.enums.EstadoUsuario;
import com.zenkai.pe.UniversidadPOO.enums.RolUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "empleados")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "id_empleado")
public abstract class Empleado extends Usuario {

    private BigDecimal sueldo;
    private int horas;
    private BigDecimal bono;

    public Empleado() {
    }

    public Empleado(BigDecimal sueldo, int horas, BigDecimal bono, int id_usuario, String codigo, String nombres, String apellidos, String dni, String numero, String correo, RolUsuario rol, String contrasena, char sexo, Date cumpleanos) {
        super(id_usuario, codigo, nombres, apellidos, dni, numero, correo, rol, contrasena, sexo, cumpleanos);
        this.sueldo = sueldo;
        this.horas = horas;
        this.bono = bono;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public BigDecimal getBono() {
        return bono;
    }

    public void setBono(BigDecimal bono) {
        this.bono = bono;
    }

    public abstract void iniciarTurno();

    public abstract void terminarTurno();

    public abstract BigDecimal calcularSueldo();
}
