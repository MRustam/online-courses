package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private IInstructorService iInstructorService;

    @Autowired
    public InstructorController(IInstructorService iInstructorService) {
        this.iInstructorService = iInstructorService;
    }

    @GetMapping("/all")
    public List<Instructor> getAll() {
        return iInstructorService.findAll();
    }

    @GetMapping("/all/{id}")
    public Instructor getOneById(@PathVariable Long id) {
        return iInstructorService.findOneById(id);
    }

    @PostMapping("/save")
    public Instructor saveOne(@RequestBody Instructor instructor) {
        return iInstructorService.saveOne(instructor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOneById(@PathVariable Long id) {
        iInstructorService.deleteOneById(id);
    }

    @PutMapping("/update/{id}")
    public Instructor updateById(@PathVariable Long id, @RequestBody Instructor instructor) {
        return iInstructorService.updateOneById(id, instructor);
    }

    //Get all students of this course
    @GetMapping("/all/{id}/courses")
    public List<Course> getAllCoursesOfThisInstructor(@PathVariable Long id) {
        return iInstructorService.getCoursesOfThisInstructor(id);
    }

}
