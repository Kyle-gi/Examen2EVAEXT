package org.biblioteca.repositorio;

import com.google.gson.reflect.TypeToken;
import org.biblioteca.entidades.Socio;
import org.biblioteca.herramientas.JsonUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlmacenSocios implements AlmacenSociosImpl {
    private final Path rutaArchivo;
    private final Type tipoListaSocios = new TypeToken<List<Socio>>(){}.getType();
    private List<Socio> listaSocios = new ArrayList<>();

    public AlmacenSocios() {
        this.rutaArchivo = Path.of("datos", "socios.json");
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
            List<Socio> temporal = JsonUtil.obtenerInstancia().fromJson(contenido, tipoListaSocios);
            if (temporal != null) {
                listaSocios = temporal;
            }
        } catch (IOException excepcion) {
            System.err.println("Error al leer socios: " + excepcion.getMessage());
        }
    }

    @Override
    public void escribirEnArchivo() {
        try {
            String json = JsonUtil.obtenerInstancia().toJson(listaSocios, tipoListaSocios);
            Files.writeString(rutaArchivo, json);
        } catch (IOException excepcion) {
            System.err.println("Error al escribir socios: " + excepcion.getMessage());
        }
    }

    @Override
    public List<Socio> consultarTodos() {
        leerDesdeArchivo();
        List<Socio> copia = new ArrayList<>();
        for (Socio s : listaSocios) {
            copia.add(s);
        }
        return copia;
    }

    @Override
    public Optional<Socio> consultarPorNumero(String numeroSocio) {
        leerDesdeArchivo();
        for (Socio s : listaSocios) {
            if (s.getNumeroSocio().equals(numeroSocio)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean guardar(Socio socio) {
        leerDesdeArchivo();
        Optional<Socio> existente = consultarPorNumero(socio.getNumeroSocio());
        if (existente.isPresent()) {
            return false;
        }
        listaSocios.add(socio);
        escribirEnArchivo();
        return true;
    }

    @Override
    public boolean borrar(String numeroSocio) {
        leerDesdeArchivo();
        boolean eliminado = false;
        for (int i = 0; i < listaSocios.size(); i++) {
            if (listaSocios.get(i).getNumeroSocio().equals(numeroSocio)) {
                listaSocios.remove(i);
                eliminado = true;
                break;
            }
        }
        if (eliminado) {
            escribirEnArchivo();
        }
        return eliminado;
    }
}

