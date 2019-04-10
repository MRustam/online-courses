package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotSavedException;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.repository.ReviewRepo;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IReviewService;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements IReviewService {

    private ReviewRepo reviewRepo;
    private CourseServiceImpl courseService;
    private UserServiceImpl userService;

    @Autowired
    public ReviewServiceImpl(ReviewRepo reviewRepo, CourseServiceImpl courseService, UserServiceImpl userService) {
        this.reviewRepo = reviewRepo;
        this.courseService = courseService;
        this.userService = userService;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    @Override
    public Review add(Long courseId, Review review, User user) throws EntityNotSavedException {
        Long userIdFromRepo = userService.findByUsername(user.getUsername()).getId();
        if (userIdFromRepo > 0 & courseId > 0 & review != null) {
            review.setCourse(courseService.findById(courseId));
            review.setOwner(user);
            return save(review);
        }
        throw new EntityNotSavedException("Review not saved");
    }

    @Override
    public Review findById(Long id) {
        Optional<Review> optReview = reviewRepo.findById(id);
        if (optReview.isPresent()) {
            return optReview.get();
        }
        throw new EntityNotFoundException("Review with id: " + id + " Not found!");
    }

    @Override
    public Review save(Review review) {
        return reviewRepo.save(review);
    }

    @Override
    public void deleteOneById(Long id) {
        reviewRepo.delete(findById(id));
    }

    @Override
    public Review update(Review review) {
        return save(review);
    }

    @Override
    public List<Review> findAllByCourseId(Long id) throws EntityNotFoundException {
        return reviewRepo.findByCourseIdOrderByCreatedDesc(id);
    }
}
