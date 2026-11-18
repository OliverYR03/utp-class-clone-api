package com.zenkai.pe.UniversidadPOO.Utils;

import com.zenkai.pe.UniversidadPOO.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class IteradorFiltroUsuarios {

    private IteradorFiltroUsuarios() {}

    public static <T extends UsuarioDTO> List<T> filtrarPorTexto(
            List<T> lista, String texto
    ) {
        List<T> resultado = new ArrayList<>(lista);

        if (texto == null || texto.isBlank()) {
            return resultado;
        }

        String buscado = texto.strip().toLowerCase(Locale.ROOT);
        Iterator<T> it = resultado.iterator();

        while (it.hasNext()) {
            T usuario = it.next();

            boolean coincide = contiene(usuario.getNombres(), buscado)
                    || contiene(usuario.getApellidos(), buscado)
                    || contiene(usuario.getCodigo(), buscado)
                    || contiene(usuario.getDni(), buscado);

            if (!coincide) {
                it.remove();
            }
        }

        return resultado;
    }

    private static boolean contiene(String campo, String buscado) {
        return campo != null
                && campo.toLowerCase(Locale.ROOT).contains(buscado);
    }
}