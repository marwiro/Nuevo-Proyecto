package services;

import entities.Student;

import java.util.List;

// ABSTRACCIÓN: la interfaz solo dice QUÉ se puede hacer, no CÓMO.
public interface IStudentService {

    Student create(Student s);

    List<Student> listAll();

    Student findOne(Long id);

    boolean update(Student s);

    boolean delete(Long id);
}
