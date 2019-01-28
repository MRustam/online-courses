package ru.rmamedov.courses.springbootappcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.model.User;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long> {

    Instructor findByUser(User user);

}
