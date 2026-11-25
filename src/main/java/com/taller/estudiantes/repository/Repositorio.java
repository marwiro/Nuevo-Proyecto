package com.taller.estudiantes.repository;

import java.util.List;
import java.util.Optional;

/**
 * PILAR: ABSTRACCIÓN. Contrato genérico de persistencia (CRUD).
 * Las capas superiores dependen de esta interfaz, no de cómo se guardan los datos.
 *
 * @param <T>  tipo de entidad
 * @param <ID> tipo del identificador
 */
public interface Repositorio<T, ID> {
    T guardar(T entidad);          // Create
    Optional<T> buscarPorId(ID id); // Read
    List<T> listarTodos();          // Read
    T actualizar(T entidad);        // Update
    boolean eliminar(ID id);        // Delete
}
