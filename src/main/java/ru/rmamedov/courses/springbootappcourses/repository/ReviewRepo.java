package ru.rmamedov.courses.springbootappcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rmamedov.courses.springbootappcourses.model.Review;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

    //Get course's reviews by id.
    @Query(nativeQuery = true, value = "SELECT * FROM course_review r WHERE r.course_id = :id")
    List<Review> getReviewsOfCourse(@Param("id") Long id);
}
