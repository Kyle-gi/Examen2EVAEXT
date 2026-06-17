package org.biblioteca.negocio;

import org.biblioteca.entidades.Prestamo;

import java.util.List;
import java.util.Optional;

public interface GestionPrestamosImpl {
    List<Prestamo> listarPrestamos();

    Optional<Prestamo> buscarPrestamo(String identificador);

    boolean realizarPrestamo(Prestamo prestamo);

    boolean devolverLibro(String identificador);
}
