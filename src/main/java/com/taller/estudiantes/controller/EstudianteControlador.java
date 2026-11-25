package com.taller.estudiantes.controller;

import com.taller.estudiantes.model.Estudiante;
import com.taller.estudiantes.service.EstudianteServicio;

import java.util.List;

/**
 * Capa de control: intermediario entre la vista y el servicio.
 * La vista (consola hoy, JavaFX mañana) solo habla con esta clase.
 */
public class EstudianteControlador {

    private final EstudianteServicio servicio;

    public EstudianteControlador(EstudianteServicio servicio) {
        this.servicio = servicio;
    }

    public Estudiante crear(String nombre, String apellido, String email, String carrera, double promedio) {
        return servicio.registrar(nombre, apellido, email, carrera, promedio);
    }

    public Estudiante buscar(int id) {
        return servicio.obtener(id);
    }

    public List<Estudiante> listar() {
        return servicio.listar();
    }

    public Estudiante editar(int id, String nombre, String apellido, String email, String carrera, double promedio) {
        return servicio.modificar(id, nombre, apellido, email, carrera, promedio);
    }

    public void borrar(int id) {
        servicio.eliminar(id);
    }
}
