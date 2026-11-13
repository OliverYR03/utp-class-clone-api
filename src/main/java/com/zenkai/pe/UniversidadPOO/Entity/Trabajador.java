package com.zenkai.pe.UniversidadPOO.Entity;

import com.zenkai.pe.UniversidadPOO.enums.CargoEmpleado;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="trabajador")
@AllArgsConstructor
@ NoArgsConstructor
public class Trabajador extends Empleado {

    private int horasExtras;
    @Enumerated(EnumType.STRING)
    private CargoEmpleado cargo;
    
    @Override
    public BigDecimal calcularSueldo() {
        BigDecimal pagoHorasExtras = BigDecimal.valueOf(horasExtras).multiply(BigDecimal.valueOf(30));
        
        return super.getSueldo().add(pagoHorasExtras);
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
