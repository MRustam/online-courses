package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.model.Student;

import java.util.List;

public interface ICourseService extends BaseInterface<Course, Long> {

    //CRUD
    @Override
    List<Course> findAll();

    @Override
    Course findOneById(Long id);

    @Override
    Course saveOne(Course object);

    @Override
    void deleteOneById(Long id);

    Course updateOne(Course course);

    // Sorted by rating
    List<Course> getAllByRating();

    //Find by title
    List<Course> findOneByTitle(String title);

    //Find all by category.
    List<Course> findByCategory(String category);

    //Get all students of this course.
    List<Student> getStudentsOfCurrentCourse(Long id);

    List<Review> getReviewsOfCurrentCourse(Long id);
}
