package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Student;

import java.util.List;

public interface IStudentService extends BaseInterface<Student, Long> {

    @Override
    List<Student> findAll();

    @Override
    Student findOneById(Long aLong);

    @Override
    Student saveOne(Student object);

    @Override
    void deleteOneById(Long aLong);

    @Override
    Student updateOneById(Long aLong, Student student);
}
