package org.biblioteca.repositorio;

import com.google.gson.reflect.TypeToken;
import org.biblioteca.entidades.Prestamo;
import org.biblioteca.herramientas.JsonUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlmacenPrestamos {
    private final Path rutaArchivo;
    private final Type tipoListaPrestamos = new TypeToken<List<Prestamo>>(){}.getType();
    private List<Prestamo> listaPrestamos = new ArrayList<>();

    public AlmacenPrestamos() {
        this.rutaArchivo = Path.of("datos", "prestamos.json");
        leerDesdeArchivo();
    }

    private void leerDesdeArchivo() {
        try {
            if (Files.notExists(rutaArchivo.getParent())) {
                Files.createDirectories(rutaArchivo.getParent());
            }
            if (Files.notExists(rutaArchivo)) {
                Files.writeString(rutaArchivo, "[]");
            }
            String contenido = Files.readString(rutaArchivo);
            List<Prestamo> temporal = JsonUtil.obtenerInstancia().fromJson(contenido, tipoListaPrestamos);
            if (temporal != null) {
                listaPrestamos = temporal;
            }
        } catch (IOException excepcion) {
            System.err.println("Error al leer préstamos: " + excepcion.getMessage());
        }
    }

    private void escribirEnArchivo() {
        try {
            String json = JsonUtil.obtenerInstancia().toJson(listaPrestamos, tipoListaPrestamos);
            Files.writeString(rutaArchivo, json);
        } catch (IOException excepcion) {
            System.err.println("Error al escribir préstamos: " + excepcion.getMessage());
        }
    }

    public List<Prestamo> consultarTodos() {
        leerDesdeArchivo();
        List<Prestamo> copia = new ArrayList<>();
        for (Prestamo p : listaPrestamos) {
            copia.add(p);
        }
        return copia;
    }

    public Optional<Prestamo> consultarPorId(String identificador) {
        leerDesdeArchivo();
        for (Prestamo p : listaPrestamos) {
            if (p.getIdentificador().equals(identificador)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public boolean guardar(Prestamo prestamo) {
        leerDesdeArchivo();
        Optional<Prestamo> existente = consultarPorId(prestamo.getIdentificador());
        if (existente.isPresent()) {
            return false;
        }
        listaPrestamos.add(prestamo);
        escribirEnArchivo();
        return true;
    }

    public boolean borrar(String identificador) {
        leerDesdeArchivo();
        boolean eliminado = false;
        for (int i = 0; i < listaPrestamos.size(); i++) {
            if (listaPrestamos.get(i).getIdentificador().equals(identificador)) {
                listaPrestamos.remove(i);
                eliminado = true;
                break;
            }
        }
        if (eliminado) {
            escribirEnArchivo();
        }
        return eliminado;
    }

    public void actualizar(Prestamo prestamo) {
        leerDesdeArchivo();
        for (int indice = 0; indice < listaPrestamos.size(); indice++) {
            if (listaPrestamos.get(indice).getIdentificador().equals(prestamo.getIdentificador())) {
                listaPrestamos.set(indice, prestamo);
                escribirEnArchivo();
                break;
            }
        }
    }
}

