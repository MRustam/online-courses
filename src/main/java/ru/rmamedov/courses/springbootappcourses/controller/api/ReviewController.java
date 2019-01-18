package ru.rmamedov.courses.springbootappcourses.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private IReviewService iReviewService;

    @Autowired
    public ReviewController(IReviewService iReviewService) {
        this.iReviewService = iReviewService;
    }

    @GetMapping("/bycourse/{id}")
    public List<Review> getAllReviewByCourseId(@PathVariable Long id) {
        return iReviewService.getAllByCourseId(id);
    }
}
