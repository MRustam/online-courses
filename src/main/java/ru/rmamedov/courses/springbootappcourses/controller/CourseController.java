package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.ICourseService;

import java.util.List;

@RestController
public class CourseController {

    private ICourseService iCourseService;

    @Autowired
    public CourseController(ICourseService iCourseService) {
        this.iCourseService = iCourseService;
    }

    @GetMapping("/courses")
    public List<Course> getAll() {
        return iCourseService.findAll();
    }

    @GetMapping("/courses/{id}")
    public Course getOneById(@PathVariable Long id) {
        return iCourseService.findOneById(id);
    }

    @PostMapping("/courses")
    public Course saveOne(@RequestBody Course course) {
        return iCourseService.saveOne(course);
    }

    @DeleteMapping("courses/{id}")
    public void deleteOneById(@PathVariable Long id) {
        iCourseService.deleteOneById(id);
    }

    @PutMapping("/courses/{id}")
    public Course updateById(@PathVariable Long id, @RequestBody Course course) {
        return iCourseService.updateOneById(id, course);
    }
}
