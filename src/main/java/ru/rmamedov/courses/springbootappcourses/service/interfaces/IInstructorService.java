package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.model.User;

import java.util.List;

public interface IInstructorService extends BaseInterface<Instructor, Long> {

    @Override
    List<Instructor> findAll();

    @Override
    Instructor findById(Long id);

    @Override
    Instructor save(Instructor instructor);

    Instructor save(User user);

    @Override
    void deleteOneById(Long id);

    @Override
    Instructor update(Instructor instructor);

    Instructor findByUsername(String username);
}
