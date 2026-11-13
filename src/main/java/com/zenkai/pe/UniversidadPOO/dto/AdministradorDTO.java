package com.zenkai.pe.UniversidadPOO.dto;

import java.math.BigDecimal;

public class AdministradorDTO extends EmpleadoDTO{
    private BigDecimal bonoGestion;

    public BigDecimal getBonoGestion() {
        return bonoGestion;
    }

    public void setBonoGestion(BigDecimal bonoGestion) {
        this.bonoGestion = bonoGestion;
    }


}
