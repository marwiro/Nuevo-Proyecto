package com.taller.estudiantes;

import com.taller.estudiantes.controller.EstudianteControlador;
import com.taller.estudiantes.repository.EstudianteRepositorio;
import com.taller.estudiantes.repository.EstudianteRepositorioMemoria;
import com.taller.estudiantes.service.EstudianteServicio;
import com.taller.estudiantes.service.EstudianteServicioImpl;
import com.taller.estudiantes.view.ConsolaEstudiantes;

/** Punto de entrada: aquí se "ensamblan" las capas (composición de dependencias). */
public class Main {
    public static void main(String[] args) {
        EstudianteRepositorio repositorio = new EstudianteRepositorioMemoria();
        EstudianteServicio servicio = new EstudianteServicioImpl(repositorio);
        EstudianteControlador controlador = new EstudianteControlador(servicio);
        new ConsolaEstudiantes(controlador).iniciar();
    }
}
