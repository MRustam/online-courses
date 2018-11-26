package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.ICourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private ICourseService iCourseService;

    @Autowired
    public CourseController(ICourseService iCourseService) {
        this.iCourseService = iCourseService;
    }

    // CRUD operations

    // Custom get all courses, sorted by rating.
    @GetMapping("/all")
    public List<Course> getAll() {
        return iCourseService.getAllByRating();
    }
    @GetMapping("/all/{id}")
    public Course getOneById(@PathVariable Long id) {
        return iCourseService.findOneById(id);
    }
    @PostMapping("/save")
    public Course saveOne(@RequestBody Course course) {
        return iCourseService.saveOne(course);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteOneById(@PathVariable Long id) {
        iCourseService.deleteOneById(id);
    }
    @PutMapping("/update/{id}")
    public Course updateById(@PathVariable Long id, @RequestBody Course course) {
        return iCourseService.updateOneById(id, course);
    }

    //Get high rated courses.
    @GetMapping("/hight")
    public List<Course> getHighRated() {
        return iCourseService.getHighRatedCourses();
    }

    //Find one by title.
    @GetMapping("/bytitle/{title}")
    public List<Course> findOneByTitle(@PathVariable String title) {
        return iCourseService.findOneByTitle(title);
    }

    //Filter by category.
    @GetMapping("/bycategory/{category}")
    public List<Course> findAllByCategory(@PathVariable String category) {
        return iCourseService.findAllByCategory(category);
    }

    //Get all students of this course
    @GetMapping("/all/{id}/students")
    public List<Student> getStudentsOfThisCourse(@PathVariable Long id) {
        return iCourseService.getStudentsOfCurrentCourse(id);
    }

    //Get all reviews from current course.
    @GetMapping("/all/{id}/reviews")
    public List<Review> getReviewsOfCourse(@PathVariable Long id) {
        return iCourseService.getReviewsOfThisCourse(id);
    }
}
