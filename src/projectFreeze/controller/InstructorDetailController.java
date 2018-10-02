package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.rmamedov.courses.springbootappcourses.model.InstructorDetail;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorDetailService;

import java.util.List;

@RestController
public class InstructorDetailController {

    private IInstructorDetailService iInstructorDetailService;

    @Autowired
    public InstructorDetailController(IInstructorDetailService iInstructorDetailService) {
        this.iInstructorDetailService = iInstructorDetailService;
    }

    @GetMapping("/instructordetail")
    public List<InstructorDetail> getAllInstructorDetailList() {
        return iInstructorDetailService.findAll();
    }

    @GetMapping("/instructordetail/{id}")
    public InstructorDetail getOneInstructorDetailListById(@PathVariable Long id) {
        return iInstructorDetailService.findOneById(id);
    }

}
