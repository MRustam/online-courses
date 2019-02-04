package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.model.User;

import java.util.List;

public interface IStudentService extends BaseInterface<Student, Long> {

    @Override
    List<Student> findAll();

    @Override
    Student findById(Long id);

    Student save(User user);

    @Override
    Student save(Student student);

    @Override
    void deleteOneById(Long id);

    @Override
    Student update(Student instructor);

    Student findByUsername(String username);
}
