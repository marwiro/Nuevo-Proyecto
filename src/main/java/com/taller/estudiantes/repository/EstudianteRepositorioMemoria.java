package com.taller.estudiantes.repository;

import com.taller.estudiantes.model.Estudiante;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * PILAR: POLIMORFISMO. Una implementación de EstudianteRepositorio en memoria.
 * Mañana se puede reemplazar por una de archivo o base de datos sin tocar el resto.
 */
public class EstudianteRepositorioMemoria implements EstudianteRepositorio {

    private final Map<Integer, Estudiante> datos = new LinkedHashMap<>();
    private int siguienteId = 1;

    @Override
    public Estudiante guardar(Estudiante e) {
        e.setId(siguienteId++);
        datos.put(e.getId(), e);
        return e;
    }

    @Override
    public Optional<Estudiante> buscarPorId(Integer id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<Estudiante> listarTodos() {
        return new ArrayList<>(datos.values()); // copia defensiva
    }

    @Override
    public Estudiante actualizar(Estudiante e) {
        datos.put(e.getId(), e);
        return e;
    }

    @Override
    public boolean eliminar(Integer id) {
        return datos.remove(id) != null;
    }

    @Override
    public Optional<Estudiante> buscarPorEmail(String email) {
        return datos.values().stream()
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}
