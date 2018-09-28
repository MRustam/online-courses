package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("instructors", iInstructorService.getAll());
        return "instructor-page";
    }
}
