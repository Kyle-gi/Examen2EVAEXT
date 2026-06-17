package org.biblioteca.negocio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.biblioteca.entidades.Socio;
import org.biblioteca.repositorio.AlmacenSocios;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class GestionSocios implements GestionSociosImpl {
    private final AlmacenSocios almacen;

    @Inject
    public GestionSocios(AlmacenSocios almacenSocios) {
        this.almacen = almacenSocios;
    }

    @Override
    public List<Socio> listarSocios() {
        return almacen.consultarTodos();
    }

    @Override
    public Optional<Socio> buscarSocio(String numeroSocio) {
        return almacen.consultarPorNumero(numeroSocio);
    }

    @Override
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

    @Override
    public boolean darDeBajaSocio(String numeroSocio) {
        return almacen.borrar(numeroSocio);
    }
}

