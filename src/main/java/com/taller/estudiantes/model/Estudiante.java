package com.taller.estudiantes.model;

/**
 * PILARES: HERENCIA (extiende Persona), POLIMORFISMO (sobrescribe describir()),
 * ENCAPSULAMIENTO (atributos privados).
 */
public class Estudiante extends Persona {

    private int id;
    private String carrera;
    private double promedio;

    public Estudiante(int id, String nombre, String apellido, String email,
                      String carrera, double promedio) {
        super(nombre, apellido, email);
        this.id = id;
        this.carrera = carrera;
        this.promedio = promedio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { this.promedio = promedio; }

    @Override
    public String describir() {
        return String.format("[%d] %s | %s | %s | Promedio: %.2f",
                id, getNombreCompleto(), getEmail(), carrera, promedio);
    }

    @Override
    public String toString() {
        return describir();
    }
}
