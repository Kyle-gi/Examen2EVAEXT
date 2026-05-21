package org.biblioteca;

import org.biblioteca.interfaz.MenuPrincipal;

import java.util.Scanner;

public class Aplicacion {
    private static final Scanner lector = new Scanner(System.in);
    private static final MenuPrincipal menu = new MenuPrincipal(lector);

    public static void main(String[] args) {
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

