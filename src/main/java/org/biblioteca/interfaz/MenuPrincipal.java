package org.biblioteca.interfaz;

import org.biblioteca.entidades.Libro;
import org.biblioteca.entidades.Prestamo;
import org.biblioteca.entidades.Socio;
import org.biblioteca.negocio.GestionLibros;
import org.biblioteca.negocio.GestionPrestamos;
import org.biblioteca.negocio.GestionSocios;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuPrincipal {
    private final Scanner lector;
    private final GestionLibros gestionLibros;
    private final GestionSocios gestionSocios;
    private final GestionPrestamos gestionPrestamos;

    public MenuPrincipal(Scanner lector) {
        this.lector = lector;
        this.gestionLibros = new GestionLibros();
        this.gestionSocios = new GestionSocios();
        this.gestionPrestamos = new GestionPrestamos();
    }

    public void mostrarMenuLibros() {
        System.out.println("\n--- Gestión de Libros ---");
        System.out.println("1) Añadir libro al catálogo");
        System.out.println("2) Eliminar libro");
        System.out.println("3) Ver catálogo completo");
        System.out.println("0) Volver");
        System.out.print("Seleccione: ");
        String eleccion = lector.nextLine().trim();
        switch (eleccion) {
            case "1" -> añadirLibro();
            case "2" -> eliminarLibro();
            case "3" -> verCatalogo();
            case "0" -> {
            }
            default -> System.out.println("Opción incorrecta");
        }
    }

    public void añadirLibro() {
        System.out.print("ISBN: ");
        String isbn = lector.nextLine().trim();
        System.out.print("Título: ");
        String titulo = lector.nextLine().trim();
        System.out.print("Autor: ");
        String autor = lector.nextLine().trim();
        System.out.print("Editorial: ");
        String editorial = lector.nextLine().trim();
        System.out.print("Ejemplares totales: ");
        String totalStr = lector.nextLine().trim();

        try {
            int total = Integer.parseInt(totalStr);
            Libro libro = new Libro(isbn, titulo, autor, editorial, total, total);
            if (gestionLibros.añadirLibro(libro)) {
                System.out.println("Libro añadido: " + isbn);
            } else {
                System.out.println("No se pudo añadir el libro (ISBN duplicado o datos inválidos)");
            }
        } catch (NumberFormatException error) {
            System.out.println("Error: número de ejemplares inválido");
        }
    }

    public void eliminarLibro() {
        System.out.print("ISBN del libro a eliminar: ");
        String isbn = lector.nextLine().trim();
        if (gestionLibros.eliminarLibro(isbn)) {
            System.out.println("Libro eliminado del catálogo");
        } else {
            System.out.println("No existe libro con ese ISBN");
        }
    }

    public void verCatalogo() {
        List<Libro> catalogo = gestionLibros.listarLibros();
        if (catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío");
        } else {
            for (Libro libro : catalogo) {
                System.out.println(libro);
            }
        }
    }

    public void mostrarMenuSocios() {
        System.out.println("\n--- Gestión de Socios ---");
        System.out.println("1) Registrar nuevo socio");
        System.out.println("2) Dar de baja socio");
        System.out.println("3) Ver lista de socios");
        System.out.println("0) Volver");
        System.out.print("Seleccione: ");
        String eleccion = lector.nextLine().trim();
        switch (eleccion) {
            case "1" -> registrarSocio();
            case "2" -> darDeBajaSocio();
            case "3" -> verListaSocios();
            case "0" -> {
            }
            default -> System.out.println("Opción incorrecta");
        }
    }

    public void registrarSocio() {
        System.out.print("Nombre: ");
        String nombre = lector.nextLine().trim();
        System.out.print("Apellidos: ");
        String apellidos = lector.nextLine().trim();
        System.out.print("Dirección: ");
        String direccion = lector.nextLine().trim();
        System.out.print("Teléfono: ");
        String telefono = lector.nextLine().trim();

        String numeroSocio = UUID.randomUUID().toString();
        Socio socio = new Socio(numeroSocio, apellidos, nombre, direccion, telefono);
        if (gestionSocios.registrarSocio(socio)) {
            System.out.println("Socio registrado con número: " + numeroSocio);
        } else {
            System.out.println("No se pudo registrar el socio (datos inválidos)");
        }
    }

    public void darDeBajaSocio() {
        System.out.print("Número de socio a dar de baja: ");
        String numeroSocio = lector.nextLine().trim();
        if (gestionSocios.darDeBajaSocio(numeroSocio)) {
            System.out.println("Socio dado de baja");
        } else {
            System.out.println("No existe socio con ese número");
        }
    }

    public void verListaSocios() {
        List<Socio> socios = gestionSocios.listarSocios();
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados");
        } else {
            for (Socio socio : socios) {
                System.out.println(socio);
            }
        }
    }

    public void mostrarMenuPrestamos() {
        System.out.println("\n--- Gestión de Préstamos ---");
        System.out.println("1) Realizar préstamo");
        System.out.println("2) Devolver libro");
        System.out.println("3) Ver préstamos activos");
        System.out.println("0) Volver");
        System.out.print("Seleccione: ");
        String eleccion = lector.nextLine().trim();
        switch (eleccion) {
            case "1" -> realizarPrestamo();
            case "2" -> devolverLibro();
            case "3" -> verPrestamosActivos();
            case "0" -> {
            }
            default -> System.out.println("Opción incorrecta");
        }
    }

    public void realizarPrestamo() {
        System.out.print("ISBN del libro: ");
        String isbn = lector.nextLine().trim();
        System.out.print("Número de socio: ");
        String numeroSocio = lector.nextLine().trim();
        System.out.print("Días de préstamo (por defecto 14): ");
        String diasStr = lector.nextLine().trim();

        try {
            int dias = diasStr.isBlank() ? 14 : Integer.parseInt(diasStr);
            LocalDate hoy = LocalDate.now();
            LocalDate devolucion = hoy.plusDays(dias);

            String identificador = UUID.randomUUID().toString();
            Prestamo prestamo = new Prestamo(identificador, isbn, numeroSocio, hoy, devolucion);

            if (gestionPrestamos.realizarPrestamo(prestamo)) {
                System.out.println("Préstamo realizado. ID: " + identificador);
                System.out.println("Fecha de devolución: " + devolucion);
            } else {
                System.out.println("No se pudo realizar el préstamo (libro no disponible o socio no existe)");
            }
        } catch (NumberFormatException error) {
            System.out.println("Error: número de días inválido");
        }
    }

    public void devolverLibro() {
        System.out.print("ID del préstamo: ");
        String identificador = lector.nextLine().trim();
        if (gestionPrestamos.devolverLibro(identificador)) {
            System.out.println("Libro devuelto correctamente");
        } else {
            System.out.println("No existe préstamo con ese ID o ya fue devuelto");
        }
    }

    public void verPrestamosActivos() {
        List<Prestamo> prestamos = gestionPrestamos.listarPrestamos();
        boolean hayActivos = false;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.isActivo()) {
                System.out.println(prestamo);
                if (prestamo.estaRetrasado()) {
                    System.out.println("  ⚠ RETRASADO");
                }
                hayActivos = true;
            }
        }
        if (!hayActivos) {
            System.out.println("No hay préstamos activos");
        }
    }
}

