package services;

import entities.Teacher;

import java.util.List;

public interface ITeacherService {

    Teacher create(Teacher t);

    List<Teacher> listAll();

    Teacher findOne(Long id);

    boolean update(Teacher t);

    boolean delete(Long id);
}
