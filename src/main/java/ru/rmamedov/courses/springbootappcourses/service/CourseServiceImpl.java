package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.repository.DTO.AllCoursesDTO;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.repository.CourseRepo;
import ru.rmamedov.courses.springbootappcourses.repository.DTO.CurrentCourseDTO;
import ru.rmamedov.courses.springbootappcourses.repository.StudentRepo;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.ICourseService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    private CourseRepo courseRepo;
    private StudentRepo studentRepo;

    @Autowired
    public CourseServiceImpl(CourseRepo courseRepo, StudentRepo studentRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    @Override
    public List<AllCoursesDTO> getAllByRating() {
        return courseRepo.findAllOrderedByRatingDesc();
    }

    @Override
    public Course findById(Long id) {
        Optional<Course> optCourse = courseRepo.findById(id);
        if (optCourse.isPresent()) {
            return optCourse.get();
        } throw new EntityNotFoundException("Course with id: " + id + " not found");
    }

    @Override
    public CurrentCourseDTO findDTOById(Long id) {
        Optional<CurrentCourseDTO> optCourse = courseRepo.findDTOById(id);
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
        for (Student student : studentRepo.findAll()) {
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
    public List<Course> findOneByTitle(String title) {
        return courseRepo.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<AllCoursesDTO> findByCategory(String category) {
        return courseRepo.findByCategoryOrderByRatingDesc(category);
    }

}
