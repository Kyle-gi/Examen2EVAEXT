package org.biblioteca.entidades;

import java.util.Objects;

public class Socio {
    private String numeroSocio;
    private String apellidos;
    private String nombre;
    private String direccion;
    private String telefono;

    public Socio() {
    }

    public Socio(String numeroSocio, String apellidos, String nombre, String direccion, String telefono) {
        this.numeroSocio = numeroSocio;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(String numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (!(otro instanceof Socio)) return false;
        Socio socio = (Socio) otro;
        return Objects.equals(numeroSocio, socio.numeroSocio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroSocio);
    }

    @Override
    public String toString() {
        return "Socio{" +
                "numeroSocio='" + numeroSocio + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}

