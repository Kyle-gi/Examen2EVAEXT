package org.biblioteca.repositorio;

import org.biblioteca.entidades.Prestamo;

import java.util.List;
import java.util.Optional;

public interface AlmacenPrestamosImpl {
    void leerDesdeArchivo();

    void escribirEnArchivo();

    List<Prestamo> consultarTodos();

    Optional<Prestamo> consultarPorId(String identificador);

    boolean guardar(Prestamo prestamo);

    boolean borrar(String identificador);

    void actualizar(Prestamo prestamo);
}
