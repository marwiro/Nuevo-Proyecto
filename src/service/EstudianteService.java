package service;

import model.Estudiante;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * POLIMORFISMO: implementa Crud<Estudiante>.
 * Guarda los datos en memoria y aplica las validaciones.
 */
public class EstudianteService implements Crud<Estudiante> {

    private final Map<Integer, Estudiante> estudiantes = new LinkedHashMap<>();
    private int siguienteId = 1;

    @Override
    public Estudiante crear(Estudiante e) {
        validar(e);
        e.setId(siguienteId++);
        estudiantes.put(e.getId(), e);
        return e;
    }

    @Override
    public Estudiante buscar(int id) {
        Estudiante e = estudiantes.get(id);
        if (e == null) {
            throw new NoSuchElementException("No existe un estudiante con id " + id);
        }
        return e;
    }

    @Override
    public List<Estudiante> listar() {
        return new ArrayList<>(estudiantes.values());
    }

    @Override
    public Estudiante actualizar(Estudiante e) {
        buscar(e.getId());
        validar(e);
        estudiantes.put(e.getId(), e);
        return e;
    }

    @Override
    public void eliminar(int id) {
        buscar(id);
        estudiantes.remove(id);
    }

    private void validar(Estudiante e) {
        if (e.getNombre() == null || e.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio");
        if (e.getApellido() == null || e.getApellido().isBlank())
            throw new IllegalArgumentException("El apellido es obligatorio");
        if (e.getEmail() == null || !e.getEmail().contains("@"))
            throw new IllegalArgumentException("El email no es válido");
        if (e.getCarrera() == null || e.getCarrera().isBlank())
            throw new IllegalArgumentException("La carrera es obligatoria");
        if (e.getPromedio() < 0 || e.getPromedio() > 5)
            throw new IllegalArgumentException("El promedio debe estar entre 0.0 y 5.0");
    }
}
