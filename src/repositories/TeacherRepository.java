package repositories;

import entities.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {

    private List<Teacher> list = new ArrayList<>();
    private Long lastId = 0L;

    public Long generateId() {
        lastId = lastId + 1;
        return lastId;
    }

    // CREATE
    public Teacher create(Teacher teacher) {
        list.add(teacher);
        return teacher;
    }

    // READ (todos)
    public List<Teacher> listAll() {
        return list;
    }

    // READ (uno). Si no existe devuelve null.
    public Teacher findOne(Long id) {
        for (Teacher teacher : list) {
            if (teacher.getId().equals(id)) {
                return teacher;
            }
        }
        return null;
    }

    // UPDATE
    public boolean update(Teacher teacher) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(teacher.getId())) {
                list.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean delete(Long id) {
        Teacher teacher = findOne(id);
        if (teacher == null) {
            return false;
        }
        list.remove(teacher);
        return true;
    }
}
