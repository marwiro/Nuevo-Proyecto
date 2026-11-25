package service;

import java.util.List;

/** ABSTRACCIÓN: contrato genérico de un CRUD. */
public interface Crud<T> {
    T crear(T entidad);
    T buscar(int id);
    List<T> listar();
    T actualizar(T entidad);
    void eliminar(int id);
}
