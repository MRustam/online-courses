package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Instructor;

import java.util.List;

public interface IInstructorService extends BaseInterface<Instructor, Long> {

    @Override
    List<Instructor> findAll();

    @Override
    Instructor findOneById(Long id);

    @Override
    Instructor saveOne(Instructor instructor);

    @Override
    void deleteOneById(Long id);

    @Override
    Instructor updateOneById(Long id, Instructor instructor);
}
