package service;

import model.Student;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * POLIMORFISMO: implementa Crud<Student>.
 * Guarda los datos en memoria y aplica las validaciones.
 */
public class StudentService implements Crud<Student> {

    private final Map<Integer, Student> students = new LinkedHashMap<>();
    private int nextId = 1;

    @Override
    public Student create(Student student) {
        validate(student);
        student.setId(nextId++);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findById(int id) {
        Student student = students.get(id);
        if (student == null) {
            throw new NoSuchElementException("No existe un estudiante con id " + id);
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student update(Student student) {
        findById(student.getId());
        validate(student);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public void delete(int id) {
        findById(id);
        students.remove(id);
    }

    private void validate(Student student) {
        if (student.getName() == null || student.getName().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio");
        if (student.getLastName() == null || student.getLastName().isBlank())
            throw new IllegalArgumentException("El apellido es obligatorio");
        if (student.getEmail() == null || !student.getEmail().contains("@"))
            throw new IllegalArgumentException("El email no es válido");
        if (student.getMajor() == null || student.getMajor().isBlank())
            throw new IllegalArgumentException("La carrera es obligatoria");
        if (student.getGpa() < 0 || student.getGpa() > 5)
            throw new IllegalArgumentException("El promedio debe estar entre 0.0 y 5.0");
    }
}
