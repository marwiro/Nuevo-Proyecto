package com.taller.estudiantes.service;

import com.taller.estudiantes.model.Estudiante;

import java.util.List;

/** PILAR: ABSTRACCIÓN. Contrato de la lógica de negocio. */
public interface EstudianteServicio {
    Estudiante registrar(String nombre, String apellido, String email, String carrera, double promedio);
    Estudiante obtener(int id);
    List<Estudiante> listar();
    Estudiante modificar(int id, String nombre, String apellido, String email, String carrera, double promedio);
    void eliminar(int id);
}
