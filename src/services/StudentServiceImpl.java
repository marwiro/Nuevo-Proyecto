package services;

import entities.Student;
import repositories.StudentRepository;

import java.util.List;

// Lógica del estudiante: validaciones y uso del repositorio.
public class StudentServiceImpl implements IStudentService {

    private StudentRepository studentRepository = new StudentRepository();

    @Override
    public Student create(Student s) {
        if (!validateFields(s)) {
            System.out.println("Hay campos que no se han llenado.");
            return null;
        }
        s.setId(studentRepository.generateId());
        return studentRepository.create(s);
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.listAll();
    }

    @Override
    public Student findOne(Long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public boolean update(Student s) {
        if (!validateFields(s)) {
            System.out.println("Hay campos que no se han llenado.");
            return false;
        }
        return studentRepository.update(s);
    }

    @Override
    public boolean delete(Long id) {
        return studentRepository.delete(id);
    }

    private boolean validateFields(Student s) {
        if (s.getName().isEmpty() || s.getGender().isEmpty() || s.getPhone().isEmpty()
                || s.getEmail().isEmpty() || s.getNumberCard().isEmpty()
                || s.getGrade().isEmpty() || s.getSubject().isEmpty()) {
            return false;
        }
        return true;
    }
}
