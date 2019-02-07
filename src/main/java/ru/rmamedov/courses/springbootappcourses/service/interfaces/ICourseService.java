package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.repository.DTO.AllCoursesDTO;
import ru.rmamedov.courses.springbootappcourses.repository.DTO.CurrentCourseDTO;

import java.util.List;

public interface ICourseService extends BaseInterface<Course, Long> {

    @Override
    List<Course> findAll();

    List<AllCoursesDTO> getAllByRating();

    CurrentCourseDTO findDTOById(Long id);

    @Override
    Course findById(Long id);

    @Override
    Course save(Course course);

    @Override
    void deleteOneById(Long id);

    @Override
    Course update(Course course);

    //Find by title
    List<Course> findOneByTitle(String title);

    //Find all by category.
    List<AllCoursesDTO> findByCategory(String category);
}
