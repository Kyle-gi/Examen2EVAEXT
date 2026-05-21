package org.biblioteca.negocio;

import org.biblioteca.entidades.Libro;
import org.biblioteca.entidades.Prestamo;
import org.biblioteca.entidades.Socio;
import org.biblioteca.repositorio.AlmacenLibros;
import org.biblioteca.repositorio.AlmacenPrestamos;
import org.biblioteca.repositorio.AlmacenSocios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class GestionPrestamos {
    private final AlmacenPrestamos almacenPrestamos;
    private final AlmacenLibros almacenLibros;
    private final AlmacenSocios almacenSocios;

    public GestionPrestamos() {
        this.almacenPrestamos = new AlmacenPrestamos();
        this.almacenLibros = new AlmacenLibros();
        this.almacenSocios = new AlmacenSocios();
    }

    public List<Prestamo> listarPrestamos() {
        return almacenPrestamos.consultarTodos();
    }

    public Optional<Prestamo> buscarPrestamo(String identificador) {
        return almacenPrestamos.consultarPorId(identificador);
    }

    public boolean realizarPrestamo(Prestamo prestamo) {
        if (prestamo.getIdentificador() == null || prestamo.getIdentificador().isBlank()) {
            return false;
        }

        Optional<Socio> socioOpt = almacenSocios.consultarPorNumero(prestamo.getNumeroSocio());
        if (socioOpt.isEmpty()) {
            return false;
        }

        Optional<Libro> libroOpt = almacenLibros.consultarPorIsbn(prestamo.getIsbnLibro());
        if (libroOpt.isEmpty()) {
            return false;
        }

        Libro libro = libroOpt.get();
        if (!libro.hayDisponibilidad()) {
            return false;
        }

        int disponibles = libro.getEjemplaresDisponibles() - 1;
        libro.setEjemplaresDisponibles(disponibles);
        almacenLibros.actualizar(libro);

        return almacenPrestamos.guardar(prestamo);
    }

    public boolean devolverLibro(String identificador) {
        Optional<Prestamo> prestamoOpt = almacenPrestamos.consultarPorId(identificador);
        if (prestamoOpt.isEmpty()) {
            return false;
        }

        Prestamo prestamo = prestamoOpt.get();
        if (!prestamo.isActivo()) {
            return false;
        }

        prestamo.setActivo(false);
        prestamo.setFechaDevolucionReal(LocalDate.now());
        almacenPrestamos.actualizar(prestamo);

        Optional<Libro> libroOpt = almacenLibros.consultarPorIsbn(prestamo.getIsbnLibro());
        if (libroOpt.isPresent()) {
            Libro libro = libroOpt.get();
            int disponibles = libro.getEjemplaresDisponibles() + 1;
            libro.setEjemplaresDisponibles(disponibles);
            almacenLibros.actualizar(libro);
        }

        return true;
    }
}

