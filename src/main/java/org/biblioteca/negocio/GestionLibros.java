package org.biblioteca.negocio;

import org.biblioteca.entidades.Libro;
import org.biblioteca.repositorio.AlmacenLibros;

import java.util.List;
import java.util.Optional;

public class GestionLibros {
    private final AlmacenLibros almacen;

    public GestionLibros() {
        this.almacen = new AlmacenLibros();
    }

    public List<Libro> listarLibros() {
        return almacen.consultarTodos();
    }

    public Optional<Libro> buscarLibro(String isbn) {
        return almacen.consultarPorIsbn(isbn);
    }

    public boolean añadirLibro(Libro libro) {
        if (libro.getIsbn() == null || libro.getIsbn().isBlank()) {
            return false;
        }
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            return false;
        }
        if (libro.getEjemplaresTotales() <= 0) {
            return false;
        }
        return almacen.guardar(libro);
    }

    public boolean eliminarLibro(String isbn) {
        return almacen.borrar(isbn);
    }

    public void modificarLibro(Libro libro) {
        almacen.actualizar(libro);
    }
}

