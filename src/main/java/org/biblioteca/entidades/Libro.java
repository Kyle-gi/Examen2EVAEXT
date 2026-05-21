package org.biblioteca.entidades;

import java.util.Objects;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private int ejemplaresDisponibles;
    private int ejemplaresTotales;

    public Libro() {
    }

    public Libro(String isbn, String titulo, String autor, String editorial, int ejemplaresTotales, int ejemplaresDisponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.ejemplaresTotales = ejemplaresTotales;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public int getEjemplaresTotales() {
        return ejemplaresTotales;
    }

    public void setEjemplaresTotales(int ejemplaresTotales) {
        this.ejemplaresTotales = ejemplaresTotales;
    }

    public boolean hayDisponibilidad() {
        return ejemplaresDisponibles > 0;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (!(otro instanceof Libro)) return false;
        Libro libro = (Libro) otro;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", ejemplaresDisponibles=" + ejemplaresDisponibles +
                ", ejemplaresTotales=" + ejemplaresTotales +
                '}';
    }
}

