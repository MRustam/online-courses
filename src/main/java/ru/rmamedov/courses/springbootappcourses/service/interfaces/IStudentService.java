package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Student;

import java.util.List;

public interface IStudentService extends UserDetailsService, BaseInterface<Student, Long> {

    @Override
    List<Student> findAll();

    @Override
    Student findOneById(Long aLong);

    @Override
    Student saveOne(Student object);

    @Override
    void deleteOneById(Long aLong);

    @Override
    Student updateOne(Student student);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
