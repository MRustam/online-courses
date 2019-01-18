package ru.rmamedov.courses.springbootappcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Review;

import java.util.List;

@Repository
public interface CourseRep extends JpaRepository<Course, Long> {

    List<Course> findTop10ByOrderByRatingDesc();

    List<Course> findByTitleContainingIgnoreCase(String title);

    List<Course> findByCategoryOrderByRatingDesc(String category);
}
