package org.biblioteca.negocio;

import org.biblioteca.entidades.Socio;

import java.util.List;
import java.util.Optional;

public interface GestionSociosImpl {
    List<Socio> listarSocios();

    Optional<Socio> buscarSocio(String numeroSocio);

    boolean registrarSocio(Socio socio);

    boolean darDeBajaSocio(String numeroSocio);
}
