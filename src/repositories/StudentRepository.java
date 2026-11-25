package repositories;

import entities.Student;

import java.util.ArrayList;
import java.util.List;

// Aquí se guardan los datos (en una lista).
public class StudentRepository {

    private List<Student> list = new ArrayList<>();
    private Long lastId = 0L;

    public Long generateId() {
        lastId = lastId + 1;
        return lastId;
    }

    // CREATE
    public Student create(Student student) {
        list.add(student);
        return student;
    }

    // READ (todos)
    public List<Student> listAll() {
        return list;
    }

    // READ (uno). Si no existe devuelve null.
    public Student findOne(Long id) {
        for (Student student : list) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // UPDATE
    public boolean update(Student student) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(student.getId())) {
                list.set(i, student);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean delete(Long id) {
        Student student = findOne(id);
        if (student == null) {
            return false;
        }
        list.remove(student);
        return true;
    }
}
