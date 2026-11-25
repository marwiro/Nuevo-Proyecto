package com.taller.estudiantes.exception;

/** Se lanza cuando los datos de un estudiante no cumplen las reglas de negocio. */
public class ValidacionException extends RuntimeException {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
