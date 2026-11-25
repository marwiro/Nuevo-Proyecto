package com.taller.estudiantes.model;

/**
 * PILARES: ABSTRACCIÓN + HERENCIA + ENCAPSULAMIENTO.
 * Clase abstracta que modela lo común a cualquier persona.
 * No se puede instanciar: solo tiene sentido a través de una subclase.
 */
public abstract class Persona {

    private String nombre;
    private String apellido;
    private String email;

    protected Persona(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    // Encapsulamiento: atributos privados, acceso por getters/setters.
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    /** Abstracción: cada subclase define cómo se describe. */
    public abstract String describir();
}
