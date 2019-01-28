package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Review;

import java.util.List;
import java.util.Optional;

public interface ICourseService extends BaseInterface<Course, Long> {

    //CRUD
    @Override
    List<Course> findAll();

    @Override
    Course findById(Long id);

    @Override
    Course save(Course object);

    @Override
    void deleteOneById(Long id);

    Course update(Course course);

    // Sorted by rating
    List<Course> getAllByRating();

    //Find by title
    List<Course> findOneByTitle(String title);

    //Find all by category.
    List<Course> findByCategory(String category);

    List<Review> getReviewsOfCurrentCourse(Long id);
}
