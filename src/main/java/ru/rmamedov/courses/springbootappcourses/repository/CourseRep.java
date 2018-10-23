package ru.rmamedov.courses.springbootappcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rmamedov.courses.springbootappcourses.model.Course;

import java.util.List;

@Repository
public interface CourseRep extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.rating >= 8.0")
    List<Course> highRated();

    @Query("SELECT c FROM Course c WHERE c.title = :title")
    Course findOneByTitle(@Param("title") String title);

    @Query("SELECT c FROM Course c WHERE c.category = :category")
    List<Course> findAllByCategory(@Param("category") String category);
}
