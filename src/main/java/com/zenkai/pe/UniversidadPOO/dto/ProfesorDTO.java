package com.zenkai.pe.UniversidadPOO.dto;

import com.zenkai.pe.UniversidadPOO.Entity.Curso;
import java.util.List;

public class ProfesorDTO extends EmpleadoDTO{
     private Double bonoCursos;
    private  List<Curso> cursos;

    public Double getBonoCursos() {
        return bonoCursos;
    }

    public void setBonoCursos(Double bonoCursos) {
        this.bonoCursos = bonoCursos;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
    
    
}
