package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Instructor;

import java.util.List;

public interface IInstructorService extends BaseInterface<Instructor, Long> {

    @Override
    List<Instructor> findAll();

    @Override
    Instructor findOneById(Long aLong);

    @Override
    Instructor saveOne(Instructor object);

    @Override
    void deleteOneById(Long aLong);
}
