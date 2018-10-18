package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.ICourseService;

@RestController
public class AddReviewToCourseController {

    private ICourseService iCourseService;

    @Autowired
    public AddReviewToCourseController(ICourseService iCourseService) {
        this.iCourseService = iCourseService;
    }

    @PostMapping("/courses/{id}")
    public Course addReview(@PathVariable Long id, @RequestBody Review review) {
        Course course = iCourseService.findOneById(id);
        course.addReview(review);
        return course;
    }
}
