package ru.rmamedov.courses.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rmamedov.courses.exception.exceptions.course.CourseAlreadyExistsException;
import ru.rmamedov.courses.exception.exceptions.course.CourseFailToSaveException;
import ru.rmamedov.courses.exception.exceptions.course.CourseNotFoundException;
import ru.rmamedov.courses.exception.exceptions.user.UserNotFoundException;
import ru.rmamedov.courses.model.course.Course;
import ru.rmamedov.courses.repository.interfaces.ICourseRepository;
import ru.rmamedov.courses.service.interfaces.ICourseService;

import java.util.List;

/**
 * @author Rustam Mamedov
 */

@Service
@Transactional
public class CourseService implements ICourseService {

    private ICourseRepository repository;

    @Autowired
    public CourseService(ICourseRepository repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public List<Course> findAll() throws CourseNotFoundException {
        return repository.findAll();
    }

    @NotNull
    @Override
    public Course findById(@NotNull String id) throws CourseNotFoundException, EmptyResultDataAccessException {
        Course course;
        try {
            course = repository.findById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException("Course with ID: '" + id + "' - Not Found");
        }
        return course;
    }

    @NotNull
    @Override
    public List<Course> findAllOrderByRatingCourses() throws CourseNotFoundException {
        return repository.findAllOrderByRatingCourses();
    }

    @NotNull
    @Override
    public List<Course> searchByHavingTitle(@NotNull final String title) throws CourseNotFoundException {
        return repository.searchByHavingTitle(title);
    }

    @Override
    public Course save(@NotNull Course course) throws DataIntegrityViolationException, CourseAlreadyExistsException {
        return repository.save(course);
    }

    @Override
    public int deleteById(@NotNull String id) throws CourseNotFoundException {
        return repository.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return repository.deleteAll();
    }

    @NotNull
    @Override
    public Course update(@NotNull final Course course) throws CourseFailToSaveException {
        return repository.update(course);
    }

    @Override
    public int fetch(@NotNull Course course) throws CourseFailToSaveException {
        return repository.fetch(course);
    }
}
