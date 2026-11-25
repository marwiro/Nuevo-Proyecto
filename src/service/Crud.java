package service;

import java.util.List;

/** ABSTRACCIÓN: contrato genérico de un CRUD. */
public interface Crud<T> {
    T create(T entity);
    T findById(int id);
    List<T> findAll();
    T update(T entity);
    void delete(int id);
}
