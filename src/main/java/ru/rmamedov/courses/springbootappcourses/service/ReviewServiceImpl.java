package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.repository.ReviewRepo;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService {

    private ReviewRepo reviewRepo;

    @Autowired
    public ReviewServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    @Override
    public Review findOneById(Long id) {
        Review review = reviewRepo.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Review with id: '" + id + "' - Not found!"));
        return review;
    }

    @Override
    public Review saveOne(Review review) {
        if (review.getText() == null || review.getText().equals("")) {
            throw new EntityNotFoundException("Saving review is null!");
        } else {
            return reviewRepo.save(review);
        }
    }

    @Override
    public void deleteOneById(Long id) {
        reviewRepo.delete(findOneById(id));
    }

    @Override
    public Review updateOneById(Long id, Review review) {
        deleteOneById(id);
        review.setId(id);
        return saveOne(review);
    }

    @Override
    public List<Review> getReviewsOfCourse(Long id) {
        return reviewRepo.getReviewsOfCourse(id);
    }
}
