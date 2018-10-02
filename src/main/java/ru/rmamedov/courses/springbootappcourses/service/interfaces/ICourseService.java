package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Course;

import java.util.List;

public interface ICourseService extends BaseInterface<Course, Long> {

    @Override
    List<Course> findAll();

    @Override
    Course findOneById(Long aLong);

    @Override
    Course saveOne(Course object);

    @Override
    void deleteOneById(Long aLong);

    @Override
    Course updateOneById(Long id, Course course);
}
