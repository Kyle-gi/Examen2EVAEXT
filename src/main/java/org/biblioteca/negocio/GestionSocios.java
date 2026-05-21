package org.biblioteca.negocio;

import org.biblioteca.entidades.Socio;
import org.biblioteca.repositorio.AlmacenSocios;

import java.util.List;
import java.util.Optional;

public class GestionSocios {
    private final AlmacenSocios almacen;

    public GestionSocios() {
        this.almacen = new AlmacenSocios();
    }

    public List<Socio> listarSocios() {
        return almacen.consultarTodos();
    }

    public Optional<Socio> buscarSocio(String numeroSocio) {
        return almacen.consultarPorNumero(numeroSocio);
    }

    public boolean registrarSocio(Socio socio) {
        if (socio.getNumeroSocio() == null || socio.getNumeroSocio().isBlank()) {
            return false;
        }
        if (socio.getApellidos() == null || socio.getApellidos().isBlank()) {
            return false;
        }
        if (socio.getNombre() == null || socio.getNombre().isBlank()) {
            return false;
        }
        return almacen.guardar(socio);
    }

    public boolean darDeBajaSocio(String numeroSocio) {
        return almacen.borrar(numeroSocio);
    }
}

