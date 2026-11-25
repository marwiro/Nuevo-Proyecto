package com.taller.estudiantes.service;

import com.taller.estudiantes.exception.EstudianteNoEncontradoException;
import com.taller.estudiantes.exception.ValidacionException;
import com.taller.estudiantes.model.Estudiante;
import com.taller.estudiantes.repository.EstudianteRepositorio;

import java.util.List;

/**
 * Capa de negocio: valida reglas y coordina con el repositorio.
 * Recibe el repositorio por constructor (inyección de dependencias) y solo
 * conoce la interfaz → bajo acoplamiento.
 */
public class EstudianteServicioImpl implements EstudianteServicio {

    private final EstudianteRepositorio repositorio;

    public EstudianteServicioImpl(EstudianteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Estudiante registrar(String nombre, String apellido, String email, String carrera, double promedio) {
        validar(nombre, apellido, email, carrera, promedio);
        if (repositorio.buscarPorEmail(email).isPresent()) {
            throw new ValidacionException("Ya existe un estudiante con ese email");
        }
        return repositorio.guardar(new Estudiante(0, nombre.trim(), apellido.trim(),
                email.trim(), carrera.trim(), promedio));
    }

    @Override
    public Estudiante obtener(int id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new EstudianteNoEncontradoException(id));
    }

    @Override
    public List<Estudiante> listar() {
        return repositorio.listarTodos();
    }

    @Override
    public Estudiante modificar(int id, String nombre, String apellido, String email, String carrera, double promedio) {
        Estudiante existente = obtener(id);
        validar(nombre, apellido, email, carrera, promedio);
        repositorio.buscarPorEmail(email).ifPresent(otro -> {
            if (otro.getId() != id) {
                throw new ValidacionException("Ya existe otro estudiante con ese email");
            }
        });
        existente.setNombre(nombre.trim());
        existente.setApellido(apellido.trim());
        existente.setEmail(email.trim());
        existente.setCarrera(carrera.trim());
        existente.setPromedio(promedio);
        return repositorio.actualizar(existente);
    }

    @Override
    public void eliminar(int id) {
        if (!repositorio.eliminar(id)) {
            throw new EstudianteNoEncontradoException(id);
        }
    }

    private void validar(String nombre, String apellido, String email, String carrera, double promedio) {
        if (vacio(nombre)) throw new ValidacionException("El nombre es obligatorio");
        if (vacio(apellido)) throw new ValidacionException("El apellido es obligatorio");
        if (vacio(email) || !email.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            throw new ValidacionException("El email no es válido");
        }
        if (vacio(carrera)) throw new ValidacionException("La carrera es obligatoria");
        if (promedio < 0 || promedio > 5) {
            throw new ValidacionException("El promedio debe estar entre 0.0 y 5.0");
        }
    }

    private boolean vacio(String s) {
        return s == null || s.isBlank();
    }
}
