package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Student;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IStudentService;

import java.util.List;

@RestController
public class StudentController {

    private IStudentService iStudentService;

    @Autowired
    public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }

    //CRUD
    @GetMapping("/students")
    public List<Student> getAll() {
        return iStudentService.findAll();
    }
    @GetMapping("/students/{id}")
    public Student getOneById(@PathVariable Long id) {
        return iStudentService.findOneById(id);
    }
    @PostMapping("/students")
    public Student saveOne(@RequestBody Student student) {
        return iStudentService.saveOne(student);
    }
    @DeleteMapping("/students/{id}")
    public void deleteOneById(@PathVariable Long id) {
        iStudentService.deleteOneById(id);
    }
    @PutMapping("/students/{id}")
    public Student updateById(@PathVariable Long id, @RequestBody Student student) {
        return iStudentService.updateOneById(id, student);
    }

    //All courses of this student.
    @GetMapping("/students/{id}/courses")
    public List<Course> getAllCoursesOfStudent(@PathVariable Long id) {
        return iStudentService.getAllCoursesOfStudent(id);
    }

}
