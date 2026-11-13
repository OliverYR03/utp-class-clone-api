package com.zenkai.pe.UniversidadPOO.Entity;

import com.zenkai.pe.UniversidadPOO.enums.RolUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="administrador")
@PrimaryKeyJoinColumn(
    name = "id_administrador",
    referencedColumnName = "id_empleado"
)
public class Administrador extends Empleado {
     
    @Column(name = "bono_gestion", precision = 10, scale = 2)
    private BigDecimal bonoGestion;

    public Administrador() {
    }

    public Administrador(BigDecimal bonoGestion) {
        this.bonoGestion = bonoGestion;
    }

    public Administrador(BigDecimal bonoGestion, BigDecimal sueldo, int horas, BigDecimal bono, int id_usuario, String codigo, String nombres, String apellidos, String dni, String numero, String correo, RolUsuario rol, String contrasena, char sexo, Date cumpleanos) {
        super(sueldo, horas, bono, id_usuario, codigo, nombres, apellidos, dni, numero, correo, rol, contrasena, sexo, cumpleanos);
        this.bonoGestion = bonoGestion;
    }

    public BigDecimal getBonoGestion() {
        return bonoGestion;
    }

    public void setBonoGestion(BigDecimal bonoGestion) {
        this.bonoGestion = bonoGestion;
    }

    
    
    @Override
    public BigDecimal calcularSueldo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void generarReporte(){
    
    }

    @Override
    public void iniciarTurno() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void terminarTurno() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
