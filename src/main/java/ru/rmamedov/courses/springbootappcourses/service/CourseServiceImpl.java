package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.repository.CourseRepo;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.ICourseService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    private CourseRepo courseRepo;
    private StudentServiceImpl studentService;

    @Autowired
    public CourseServiceImpl(CourseRepo courseRepo, StudentServiceImpl studentService) {
        this.courseRepo = courseRepo;
        this.studentService = studentService;
    }

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course findById(Long id) {
        Optional<Course> optCourse = courseRepo.findById(id);
        if (optCourse.isPresent()) {
            return optCourse.get();
        } throw new EntityNotFoundException("Course with id: " + id + " not found");
    }

    @Override
    public Course save(Course course) {
        if (course == null) {
            throw new EntityNotFoundException("Saving course is null!");
        }
        return courseRepo.save(course);
    }

    @Override
    public void deleteOneById(Long id) {
        Course course = findById(id);
        for (Student student : studentService.findAll()) {
            if (student.getCourses() != null) {
                student.getCourses().remove(course);
            }
        }
        courseRepo.delete(course);
    }

    @Override
    public Course update(Course course) {
        return save(course);
    }


    @Override
    public List<Course> getAllByRating() {
        return courseRepo.findTop20ByOrderByRatingDesc();
    }

    @Override
    public List<Course> findOneByTitle(String title) {
        return courseRepo.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Course> findByCategory(String category) {
        return courseRepo.findByCategoryOrderByRatingDesc(category);
    }

}
