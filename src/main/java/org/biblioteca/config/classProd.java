package org.biblioteca.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.biblioteca.repositorio.AlmacenLibros;

import java.util.Scanner;

@ApplicationScoped
public class classProd {
    @Produces
    public Scanner process() {
        return new Scanner(System.in);
    }
}
