package org.biblioteca.entidades;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {
    private String identificador;
    private String isbnLibro;
    private String numeroSocio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;
    private boolean activo;

    public Prestamo() {
    }

    public Prestamo(String identificador, String isbnLibro, String numeroSocio, 
                    LocalDate fechaPrestamo, LocalDate fechaDevolucionPrevista) {
        this.identificador = identificador;
        this.isbnLibro = isbnLibro;
        this.numeroSocio = numeroSocio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.activo = true;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(String numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }

    public void setFechaDevolucionPrevista(LocalDate fechaDevolucionPrevista) {
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean estaRetrasado() {
        if (!activo) return false;
        return LocalDate.now().isAfter(fechaDevolucionPrevista);
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (!(otro instanceof Prestamo)) return false;
        Prestamo prestamo = (Prestamo) otro;
        return Objects.equals(identificador, prestamo.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "identificador='" + identificador + '\'' +
                ", isbnLibro='" + isbnLibro + '\'' +
                ", numeroSocio='" + numeroSocio + '\'' +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucionPrevista=" + fechaDevolucionPrevista +
                ", fechaDevolucionReal=" + fechaDevolucionReal +
                ", activo=" + activo +
                '}';
    }
}

