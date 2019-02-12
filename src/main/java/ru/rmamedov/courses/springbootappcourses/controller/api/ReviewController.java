package ru.rmamedov.courses.springbootappcourses.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.model.User;
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
    public ResponseEntity<List<Review>> getAllReviewByCourseId(@PathVariable Long id) {
        if (iReviewService.findAllByCourseId(id).size() > 0) {
            return new ResponseEntity<>(iReviewService.findAllByCourseId(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add/{courseId}")
    public ResponseEntity<Review> add(@PathVariable Long courseId,
                                      @RequestBody Review review,
                                      @AuthenticationPrincipal User user) {
        if (courseId > 0 & user != null & review != null) {
            return new ResponseEntity<>(iReviewService.add(courseId, review, user), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
