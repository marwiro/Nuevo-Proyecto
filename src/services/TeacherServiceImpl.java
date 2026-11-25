package services;

import entities.Teacher;
import repositories.TeacherRepository;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService {

    private TeacherRepository teacherRepository = new TeacherRepository();

    @Override
    public Teacher create(Teacher t) {
        if (!validateFields(t)) {
            System.out.println("Hay campos que no se han llenado.");
            return null;
        }
        t.setId(teacherRepository.generateId());
        return teacherRepository.create(t);
    }

    @Override
    public List<Teacher> listAll() {
        return teacherRepository.listAll();
    }

    @Override
    public Teacher findOne(Long id) {
        return teacherRepository.findOne(id);
    }

    @Override
    public boolean update(Teacher t) {
        if (!validateFields(t)) {
            System.out.println("Hay campos que no se han llenado.");
            return false;
        }
        return teacherRepository.update(t);
    }

    @Override
    public boolean delete(Long id) {
        return teacherRepository.delete(id);
    }

    private boolean validateFields(Teacher t) {
        if (t.getName().isEmpty() || t.getGender().isEmpty() || t.getPhone().isEmpty()
                || t.getEmail().isEmpty() || t.getSubject().isEmpty()) {
            return false;
        }
        return true;
    }
}
