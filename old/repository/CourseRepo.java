package ru.rmamedov.courses.springbootappcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.repository.DTO.AllCoursesDTO;
import ru.rmamedov.courses.springbootappcourses.repository.DTO.CurrentCourseDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    String sql1 =
            "SELECT c.id,\n" +
            "       c.title,\n" +
            "       concat(substring(c.description, 1, 20), '...') AS description,\n" +
            "       i.rating AS rating,\n" +
            "       c.rating AS score,\n" +
            "       count(sc.student_id) AS enrolledCourses\n" +
            "FROM course c\n" +
            "  JOIN instructor i ON c.instructor_id = i.id\n" +
            "  LEFT JOIN student_course sc ON c.id = sc.course_id ";

    String sql2 =
            "GROUP BY\n" +
            "         c.id,\n" +
            "         c.title,\n" +
            "         c.description,\n" +
            "         c.rating,\n" +
            "         i.rating\n" +
            "ORDER BY c.rating DESC;";

    String sql3 =
            "SELECT c.title,\n" +
                    "       c.description,\n" +
                    "       c.rating,\n" +
                    "       c.start_date    AS date,\n" +
                    "       c.duration,\n" +
                    "       c.category,\n" +
                    "       c.status,\n" +
                    "       u.full_name AS owner,\n" +
                    "       count(sc.student_id) AS enrolledCourses,\n" +
                    "       rcount.count AS rcount\n" +
                    "FROM course c\n" +
                    "       LEFT JOIN student_course sc ON c.id = sc.course_id\n" +
                    "       JOIN instructor ir ON c.instructor_id = ir.id\n" +
                    "       JOIN users u ON ir.user_id = u.id\n" +
                    "       LEFT JOIN (SELECT r.course_id,\n" +
                    "                         count(r.course_id) AS count\n" +
                    "                    FROM review r\n" +
                    "                    GROUP BY r.course_id\n" +
                    "                 ) AS rcount ON c.id = rcount.course_id\n" +
                    "WHERE c.id = :id\n" +
                    "GROUP BY c.title,\n" +
                    "         c.description,\n" +
                    "         c.rating,\n" +
                    "         c.start_date,\n" +
                    "         c.duration,\n" +
                    "         c.category,\n" +
                    "         c.status,\n" +
                    "         c.instructor_id,\n" +
                    "         rcount.count,\n" +
                    "         u.full_name\n" +
                    "ORDER BY c.rating DESC;";


    @Query(value = sql1 + sql2, nativeQuery = true)
    List<AllCoursesDTO> findAllOrderedByRatingDesc();

    List<Course> findByTitleContainingIgnoreCase(String title);

    @Query(value = sql1 + " WHERE c.category LIKE :category " + sql2, nativeQuery = true)
    List<AllCoursesDTO> findByCategoryOrderByRatingDesc(@Param("category") String category);

    @Query(value = sql3, nativeQuery = true)
    Optional<CurrentCourseDTO> findDTOById(@Param("id") Long id);

    @Query(value = sql1 + " WHERE sc.student_id = :id " + sql2, nativeQuery = true)
    List<AllCoursesDTO> findByStudentIdOrderByRatingDesc(@Param("id") Long id);
}
