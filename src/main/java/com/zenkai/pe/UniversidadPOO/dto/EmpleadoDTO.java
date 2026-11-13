package com.zenkai.pe.UniversidadPOO.dto;

import java.math.BigDecimal;

/**
 *
 * @author Oliver Cavsa
 */
public class EmpleadoDTO extends UsuarioDTO{

    private BigDecimal sueldo;
    private int horas;
    private BigDecimal bono;

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
    
    
}
