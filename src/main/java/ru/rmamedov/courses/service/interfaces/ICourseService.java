package ru.rmamedov.courses.service.interfaces;

import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.rmamedov.courses.exception.exceptions.course.CourseAlreadyExistsException;
import ru.rmamedov.courses.exception.exceptions.course.CourseFailToSaveException;
import ru.rmamedov.courses.exception.exceptions.course.CourseNotFoundException;
import ru.rmamedov.courses.model.course.Course;
import ru.rmamedov.courses.repository.interfaces.base.IBaseRepository;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

public interface ICourseService extends IBaseRepository<Course, String> {
    @NotNull
    @Override
    List<Course> findAll() throws CourseNotFoundException;

    @NotNull
    List<Course> findAllOrderByRatingCourses() throws CourseNotFoundException;

    @NotNull
    List<Course> searchByHavingTitle(@NotNull final String title) throws CourseNotFoundException;

    @NotNull
    @Override
    Course findById(@NotNull final String s) throws CourseNotFoundException, EmptyResultDataAccessException;

    @Override
    Course save(@NotNull final Course course) throws DataIntegrityViolationException, CourseAlreadyExistsException;

    @Override
    int deleteById(@NotNull final String s) throws CourseNotFoundException;

    int deleteAll();

    @NotNull
    @Override
    Course update(@NotNull final Course course) throws CourseFailToSaveException;

    @Override
    int fetch(@NotNull final Course course) throws CourseFailToSaveException;
}
