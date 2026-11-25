package com.taller.estudiantes.repository;

import com.taller.estudiantes.model.Estudiante;

import java.util.Optional;

/** Contrato específico para estudiantes (agrega consultas propias del dominio). */
public interface EstudianteRepositorio extends Repositorio<Estudiante, Integer> {
    Optional<Estudiante> buscarPorEmail(String email);
}
