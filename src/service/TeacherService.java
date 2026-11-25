package service;

import java.util.ArrayList;
import model.Teacher;

// Lógica del CRUD de profesores. Es igual al de estudiantes pero con Teacher.
public class TeacherService {

    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    private int nextId = 1;

    public String validate(Teacher teacher) {
        if (teacher.getName().equals("")) return "El nombre es obligatorio";
        if (teacher.getLastName().equals("")) return "El apellido es obligatorio";
        if (!teacher.getEmail().contains("@")) return "El email no es válido";
        if (teacher.getSubject().equals("")) return "La materia es obligatoria";
        return "";
    }

    // CREATE
    public void create(Teacher teacher) {
        teacher.setId(nextId);
        nextId = nextId + 1;
        teachers.add(teacher);
    }

    // READ (uno)
    public Teacher findById(int id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                return teachers.get(i);
            }
        }
        return null;
    }

    // READ (todos)
    public ArrayList<Teacher> findAll() {
        return teachers;
    }

    // UPDATE
    public boolean update(Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == teacher.getId()) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean delete(int id) {
        Teacher teacher = findById(id);
        if (teacher == null) {
            return false;
        }
        teachers.remove(teacher);
        return true;
    }
}
