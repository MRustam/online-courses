package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IReviewService;

import java.util.List;

@RestController
public class ReviewController {

    private IReviewService iReviewService;

    @Autowired
    public ReviewController(IReviewService iReviewService) {
        this.iReviewService = iReviewService;
    }

    @GetMapping("/reviews")
    public List<Review> getAll() {
        return iReviewService.findAll();
    }

    @GetMapping("/reviews/{id}")
    public Review getOneById(@PathVariable Long id) {
        return iReviewService.findOneById(id);
    }

    @PostMapping("/reviews")
    public Review saveOne(@RequestBody Review review) {
        return iReviewService.saveOne(review);
    }

    @DeleteMapping("reviews/{id}")
    public void deleteOneById(@PathVariable Long id) {
        iReviewService.deleteOneById(id);
    }

    @PutMapping("/reviews/{id}")
    public Review updateById(@PathVariable Long id, @RequestBody Review review) {
        return iReviewService.updateOneById(id, review);
    }

    //Get all from current course.
    @GetMapping("/reviews/ofcourse/{courseId}")
    public List<Review> getReviewsOfCourse(@PathVariable Long courseId) {
        return iReviewService.getReviewsOfCourse(courseId);
    }
}
