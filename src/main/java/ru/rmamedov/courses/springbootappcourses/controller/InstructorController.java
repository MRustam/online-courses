package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

import java.util.List;

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

//    @GetMapping("/form")
//    public String addInstructor(Model model) {
//        model.addAttribute("instructor", new Instructor());
//        return "instructor-form";
//    }
//
//    @PostMapping("/form")
//    public String addInstructor(@ModelAttribute Instructor instructor, Model model) {
//        iInstructorService.saveOne(instructor);
//        model.addAttribute("instructor", instructor);
//        return "success";
//    }
}
