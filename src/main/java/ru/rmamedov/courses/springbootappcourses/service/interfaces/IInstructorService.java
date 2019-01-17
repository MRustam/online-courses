package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;

import java.util.List;

public interface IInstructorService extends UserDetailsService, BaseInterface<Instructor, Long> {

    @Override
    List<Instructor> findAll();
    @Override
    Instructor findOneById(Long id);
    @Override
    Instructor saveOne(Instructor instructor);
    @Override
    void deleteOneById(Long id);
    @Override
    Instructor updateOne(Instructor instructor);


    List<Course> getExistingCourses(Long id);


    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
