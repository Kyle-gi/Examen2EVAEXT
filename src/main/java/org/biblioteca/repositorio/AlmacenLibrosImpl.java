package org.biblioteca.repositorio;

import org.biblioteca.entidades.Libro;

import java.util.List;
import java.util.Optional;

public interface AlmacenLibrosImpl {
    void leerDesdeArchivo();

    void escribirEnArchivo();

    List<Libro> consultarTodos();

    Optional<Libro> consultarPorIsbn(String isbn);

    boolean guardar(Libro libro);

    boolean borrar(String isbn);

    void actualizar(Libro libro);
}
