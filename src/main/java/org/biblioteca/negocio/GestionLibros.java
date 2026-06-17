package org.biblioteca.negocio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.biblioteca.entidades.Libro;
import org.biblioteca.repositorio.AlmacenLibros;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class GestionLibros implements GestionLibrosImpl {
    private final AlmacenLibros almacen;

    @Inject
    public GestionLibros(AlmacenLibros almacenLibros) {
        this.almacen = almacenLibros;
    }

    @Override
    public List<Libro> listarLibros() {
        return almacen.consultarTodos();
    }

    @Override
    public Optional<Libro> buscarLibro(String isbn) {
        return almacen.consultarPorIsbn(isbn);
    }

    @Override
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

    @Override
    public boolean eliminarLibro(String isbn) {
        return almacen.borrar(isbn);
    }

    @Override
    public void modificarLibro(Libro libro) {
        almacen.actualizar(libro);
    }
}

