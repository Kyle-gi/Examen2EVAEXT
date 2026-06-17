package org.biblioteca.repositorio;

import org.biblioteca.entidades.Socio;

import java.util.List;
import java.util.Optional;

public interface AlmacenSociosImpl {
    void leerDesdeArchivo();

    void escribirEnArchivo();

    List<Socio> consultarTodos();

    Optional<Socio> consultarPorNumero(String numeroSocio);

    boolean guardar(Socio socio);

    boolean borrar(String numeroSocio);
}
