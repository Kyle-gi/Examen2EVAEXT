package org.biblioteca.repositorio;

import com.google.gson.reflect.TypeToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.biblioteca.entidades.Libro;
import org.biblioteca.herramientas.JsonUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class AlmacenLibros implements AlmacenLibrosImpl {
    private final Path rutaArchivo;
    private final Type tipoListaLibros = new TypeToken<List<Libro>>(){}.getType();
    private List<Libro> listaLibros = new ArrayList<>();

    @Inject
    public AlmacenLibros() {
        this.rutaArchivo = Path.of("datos", "libros.json");
        leerDesdeArchivo();
    }

    @Override
    public void leerDesdeArchivo() {
        try {
            if (Files.notExists(rutaArchivo.getParent())) {
                Files.createDirectories(rutaArchivo.getParent());
            }
            if (Files.notExists(rutaArchivo)) {
                Files.writeString(rutaArchivo, "[]");
            }
            String contenido = Files.readString(rutaArchivo);
            List<Libro> temporal = JsonUtil.obtenerInstancia().fromJson(contenido, tipoListaLibros);
            if (temporal != null) {
                listaLibros = temporal;
            }
        } catch (IOException excepcion) {
            System.err.println("Error al leer libros: " + excepcion.getMessage());
        }
    }

    @Override
    public void escribirEnArchivo() {
        try {
            String json = JsonUtil.obtenerInstancia().toJson(listaLibros, tipoListaLibros);
            Files.writeString(rutaArchivo, json);
        } catch (IOException excepcion) {
            System.err.println("Error al escribir libros: " + excepcion.getMessage());
        }
    }

    @Override
    public List<Libro> consultarTodos() {
        leerDesdeArchivo();
        List<Libro> copia = new ArrayList<>();
        for (Libro l : listaLibros) {
            copia.add(l);
        }
        return copia;
    }

    @Override
    public Optional<Libro> consultarPorIsbn(String isbn) {
        leerDesdeArchivo();
        for (Libro l : listaLibros) {
            if (l.getIsbn().equals(isbn)) {
                return Optional.of(l);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean guardar(Libro libro) {
        leerDesdeArchivo();
        Optional<Libro> existente = consultarPorIsbn(libro.getIsbn());
        if (existente.isPresent()) {
            return false;
        }
        listaLibros.add(libro);
        escribirEnArchivo();
        return true;
    }

    @Override
    public boolean borrar(String isbn) {
        leerDesdeArchivo();
        boolean eliminado = false;
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getIsbn().equals(isbn)) {
                listaLibros.remove(i);
                eliminado = true;
                break;
            }
        }
        if (eliminado) {
            escribirEnArchivo();
        }
        return eliminado;
    }

    @Override
    public void actualizar(Libro libro) {
        leerDesdeArchivo();
        for (int indice = 0; indice < listaLibros.size(); indice++) {
            if (listaLibros.get(indice).getIsbn().equals(libro.getIsbn())) {
                listaLibros.set(indice, libro);
                escribirEnArchivo();
                break;
            }
        }
    }
}

