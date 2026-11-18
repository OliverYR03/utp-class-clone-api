package com.zenkai.pe.UniversidadPOO.Utils;

import java.util.ArrayList;
import java.util.List;


public class Paginador<T> {
    private List<T> datos = new ArrayList<>();
    private int paginaActual = 1;
    private int filasPorPagina = 20;
    
    public void establecerDatos(List<T> nuevosDatos){
        datos = nuevosDatos == null
                ? new ArrayList<>()
                : new ArrayList<>(nuevosDatos);
        paginaActual = 1;
    }
    
    public void establecerFilasPorPagina(int filas){
        if (filas <= 0 ) {
            throw new IllegalArgumentException("Las filas deben ser positivas");
        }
        filasPorPagina = filas;
        paginaActual = 1;
    }
    
   public int getPaginaActual() {
        return paginaActual;
    }

    public int getTotalRegistros() {
        return datos.size();
    }
    
    public int getTotalPaginas(){
        return Math.max(1, (datos.size() + filasPorPagina - 1) /  filasPorPagina);
    }
    
    public List<T> obtenerPagina() {
        int inicio = (paginaActual - 1 ) * filasPorPagina;
        int fin = Math.min(inicio + filasPorPagina, datos.size());
        return new ArrayList<>(datos.subList(inicio,fin));
    }
    
    public void primera(){
        paginaActual = 1;
    }
    
    public void anterior() {
        if(paginaActual > 1 ) paginaActual--;
    }
    
    public void siguiente(){
        if ( paginaActual < getTotalPaginas()) paginaActual++;
    }
    
    public void ultimna() {
        paginaActual = getTotalPaginas();
    }
    
    public boolean hayAnterior() {
        return paginaActual > 1;
    }

    public boolean haySiguiente() {
        return paginaActual < getTotalPaginas();
    }

    public void irAPagina(int pagina) {
    if (pagina < 1 || pagina > getTotalPaginas()) {
        throw new IllegalArgumentException("Página fuera de rango");
    }

    paginaActual = pagina;
}
}
