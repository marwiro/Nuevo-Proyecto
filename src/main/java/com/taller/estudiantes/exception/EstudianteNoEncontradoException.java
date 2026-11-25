package com.taller.estudiantes.exception;

/** Se lanza cuando se busca un estudiante que no existe. */
public class EstudianteNoEncontradoException extends RuntimeException {
    public EstudianteNoEncontradoException(int id) {
        super("No existe un estudiante con id " + id);
    }
}
