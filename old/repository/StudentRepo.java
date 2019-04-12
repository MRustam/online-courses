package ru.rmamedov.courses.springbootappcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.repository.DTO.StudentDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    String sql1 = "SELECT\n" +
            "      s.id,\n" +
            "      u.full_name AS fullname,\n" +
            "      u.username,\n" +
            "      u.age,\n" +
            "      u.registered,\n" +
            "      u.email,\n" +
            "      u.skype,\n" +
            "      s.academic_performance AS performance,\n" +
            "      count(sc.course_id) AS ru.rmamedov.createdCourses\n" +
            "FROM student s\n" +
            "JOIN users u ON s.user_id = u.id\n" +
            "LEFT JOIN student_course sc on s.id = sc.student_id\n";

    String sql2 = "GROUP BY s.id,\n" +
            "         u.full_name,\n" +
            "         u.username,\n" +
            "         u.age,\n" +
            "         u.registered,\n" +
            "         u.email,\n" +
            "         u.skype,\n" +
            "         s.academic_performance;";

    Student findByUser(User user);


    @Query(value = sql1 + " WHERE s.id = :id " + sql2, nativeQuery = true)
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);

    @Query(value = sql1 + " WHERE sc.course_id = :id " + sql2, nativeQuery = true)
    List<StudentDTO> findStudentDTOByCourseId(@Param("id") Long id);

    @Query(value = sql1 + sql2, nativeQuery = true)
    List<StudentDTO> findAllDTO();

}
