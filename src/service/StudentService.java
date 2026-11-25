package service;

import java.util.ArrayList;
import model.Student;

// Lógica del CRUD de estudiantes. Los datos se guardan en una lista.
public class StudentService {

    private ArrayList<Student> students = new ArrayList<Student>();
    private int nextId = 1;

    // Devuelve un texto con el error, o "" si todo está bien.
    public String validate(Student student) {
        if (student.getName().equals("")) return "El nombre es obligatorio";
        if (student.getLastName().equals("")) return "El apellido es obligatorio";
        if (!student.getEmail().contains("@")) return "El email no es válido";
        if (student.getMajor().equals("")) return "La carrera es obligatoria";
        if (student.getGpa() < 0 || student.getGpa() > 5) return "El promedio debe estar entre 0 y 5";
        return "";
    }

    // CREATE
    public void create(Student student) {
        student.setId(nextId);
        nextId = nextId + 1;
        students.add(student);
    }

    // READ (uno)
    public Student findById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return students.get(i);
            }
        }
        return null;
    }

    // READ (todos)
    public ArrayList<Student> findAll() {
        return students;
    }

    // UPDATE
    public boolean update(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean delete(int id) {
        Student student = findById(id);
        if (student == null) {
            return false;
        }
        students.remove(student);
        return true;
    }
}
