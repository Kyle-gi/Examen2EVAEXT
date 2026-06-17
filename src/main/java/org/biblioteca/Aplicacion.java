package org.biblioteca;

import org.biblioteca.interfaz.MenuPrincipal;
import org.biblioteca.negocio.GestionLibros;
import org.biblioteca.negocio.GestionPrestamos;
import org.biblioteca.negocio.GestionSocios;
import org.biblioteca.repositorio.AlmacenLibros;
import org.biblioteca.repositorio.AlmacenPrestamos;
import org.biblioteca.repositorio.AlmacenSocios;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Scanner;

public class Aplicacion {
    private static final Scanner lector = new Scanner(System.in);

    private static final AlmacenLibros almacenLibros = new AlmacenLibros();
    private static final AlmacenPrestamos almacenPrestamos = new AlmacenPrestamos();
    private static final AlmacenSocios  almacenSocios = new AlmacenSocios();


    private static final GestionSocios  gestionSocios = new GestionSocios(almacenSocios);
    private static final GestionLibros gestionLibros = new GestionLibros(almacenLibros);
    private static final GestionPrestamos gestionPrestamos = new GestionPrestamos(almacenPrestamos, almacenLibros, almacenSocios);


    private static final MenuPrincipal menu = new MenuPrincipal(lector, gestionLibros, gestionSocios, gestionPrestamos);

    public static void main(String[] args) {

        WeldContainer container = new Weld().initialize();
        MenuPrincipal menuPrincipal = container.select(MenuPrincipal.class).get();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   SISTEMA DE GESTIÓN BIBLIOTECARIA    ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1) Gestión de Libros");
            System.out.println("2) Gestión de Socios");
            System.out.println("3) Gestión de Préstamos");
            System.out.println("0) Salir del sistema");
            System.out.print("Opción: ");
            String opcion = lector.nextLine().trim();
            switch (opcion) {
                case "1" -> menu.mostrarMenuLibros();
                case "2" -> menu.mostrarMenuSocios();
                case "3" -> menu.mostrarMenuPrestamos();
                case "0" -> continuar = false;
                default -> System.out.println("Opción incorrecta");
            }
        }
        System.out.println("\n¡Gracias por usar el sistema!");
    }
}

