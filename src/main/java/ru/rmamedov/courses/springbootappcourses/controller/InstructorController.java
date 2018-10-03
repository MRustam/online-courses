package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

import java.util.List;

@RestController
public class InstructorController {

    private IInstructorService iInstructorService;

    @Autowired
    public InstructorController(IInstructorService iInstructorService) {
        this.iInstructorService = iInstructorService;
    }

    @GetMapping("/instructors")
    public List<Instructor> getAll() {
        return iInstructorService.findAll();
    }

    @GetMapping("/instructors/{id}")
    public Instructor getOneById(@PathVariable Long id) {
        return iInstructorService.findOneById(id);
    }

    @PostMapping("/instructors")
    public Instructor saveOne(@RequestBody Instructor instructor) {
        return iInstructorService.saveOne(instructor);
    }

    @DeleteMapping("/instructors/{id}")
    public void deleteOneById(@PathVariable Long id) {
        iInstructorService.deleteOneById(id);
    }

    @PutMapping("/instructors/{id}")
    public Instructor updateById(@PathVariable Long id, @RequestBody Instructor instructor) {
        return iInstructorService.updateOneById(id, instructor);
    }

}
