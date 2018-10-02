package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

@Controller
public class InstructorController {

    private IInstructorService iInstructorService;

    @Autowired
    public InstructorController(IInstructorService iInstructorService) {
        this.iInstructorService = iInstructorService;
    }

    @GetMapping("/instructors")
    public String getAllInstructors(Model model) {
        model.addAttribute("instructors", iInstructorService.findAll());
        return "instructors";
    }

    @GetMapping("/instructors/{id}")
    public String getOneInstructorsById(Model model, @PathVariable Long id) {
        model.addAttribute("instructors", iInstructorService.findOneById(id));
        return "instructors";
    }

    @GetMapping("/form")
    public String addInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "form";
    }

    @PostMapping("/form")
    public String addInstructor(@ModelAttribute Instructor instructor, Model model) {
        iInstructorService.saveOne(instructor);
        model.addAttribute("instructor", instructor);
        return "success";
    }

//    @DeleteMapping("/instructors/{id}")
//    public String deleteOneById(@PathVariable Long id) {
//        Long instId = iInstructorService.findOneById(id).getId();
//        iInstructorService.deleteOneById(instId);
//        return "instructors";
//    }
}
