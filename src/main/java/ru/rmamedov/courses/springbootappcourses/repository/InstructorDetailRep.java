package ru.rmamedov.courses.springbootappcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rmamedov.courses.springbootappcourses.model.InstructorDetail;

@Repository
public interface InstructorDetailRep extends JpaRepository<InstructorDetail, Long> {
}
