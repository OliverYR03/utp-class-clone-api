package com.zenkai.pe.UniversidadPOO.Entity;

import com.zenkai.pe.UniversidadPOO.enums.TipoAula;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aula")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_aula;
    @Enumerated(EnumType.STRING)
    private TipoAula tipo;
    private String codigo;
    private int aforo;
    @Column(name="esDisponible")
    private boolean esDisponible;
    
    public Aula(){
    }
    
    public Aula(TipoAula tipo, String codigo, int aforo) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.aforo = aforo;
        this.esDisponible = true;
    }

    public int getId_aula() {
        return id_aula;
    }

    public TipoAula getTipo() {
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getAforo() {
        return aforo;
    }

    public boolean isEsDisponible() {
        return esDisponible;
    }
    
    
    
}
