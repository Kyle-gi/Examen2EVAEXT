package org.biblioteca.negocio;

import org.biblioteca.entidades.Libro;

import java.util.List;
import java.util.Optional;

public interface GestionLibrosImpl {
    List<Libro> listarLibros();

    Optional<Libro> buscarLibro(String isbn);

    boolean añadirLibro(Libro libro);

    boolean eliminarLibro(String isbn);

    void modificarLibro(Libro libro);
}
