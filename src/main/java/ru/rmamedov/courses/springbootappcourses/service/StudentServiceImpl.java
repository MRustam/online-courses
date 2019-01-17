package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.repository.StudentRep;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IStudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private StudentRep studentRep;
    private CourseServiceImpl courseService;

    @Autowired
    public StudentServiceImpl(StudentRep studentRep, CourseServiceImpl courseService) {
        this.studentRep = studentRep;
        this.courseService = courseService;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findOneById(Long aLong) {
        return null;
    }

    @Override
    public Student saveOne(Student object) {
        return null;
    }

    @Override
    public void deleteOneById(Long aLong) {

    }

    @Override
    public Student updateOne(Student student) {
        return null;
    }

    @Override
    public List<Course> getAllCoursesOfCurrentStudent(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRep.findByUsername(username);
        if (student != null) {
            return student;
        }
        throw new UsernameNotFoundException("User " + username + " not found.");
    }

}
