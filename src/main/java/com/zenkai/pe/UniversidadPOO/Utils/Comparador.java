package com.zenkai.pe.UniversidadPOO.Utils;

import java.util.Comparator;
import java.util.function.Function;
/**
 *
 * @author Oliver Cavsa
 */
public class Comparador<T, U> implements Comparator<T> {

    private final Comparator<T> comparador;

    public Comparador(
        Function<T, U> obtenerValor,
        Comparator<U> criterio,
        boolean ascendente
) {
    Comparator<U> orden = ascendente ? criterio : criterio.reversed();
    this.comparador = Comparator.comparing(
            obtenerValor,
            Comparator.nullsLast(orden)
    );
}

    @Override
    public int compare(T primero, T segundo) {
        return comparador.compare(primero, segundo);
    }
}
