package com.zenkai.pe.UniversidadPOO.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zenkai.pe.UniversidadPOO.enums.EstadoUsuario;
import com.zenkai.pe.UniversidadPOO.enums.RolUsuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;

@Data

public class UsuarioDTO {
    private String codigo;
    private String nombres, apellidos, dni, numero, correo;
    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;
    @Enumerated(EnumType.STRING)
    private RolUsuario rol;
    private char sexo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date cumpleanos;
    
    
}
