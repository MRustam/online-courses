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
        return null;
    }

    @Override
    public Review findOneById(Long aLong) {
        return null;
    }

    @Override
    public Review saveOne(Review object) {
        return null;
    }

    @Override
    public void deleteOneById(Long aLong) {

    }

    @Override
    public Review updateOne(Review review) {
        return null;
    }

    @Override
    public List<Review> getAllByCourseId(Long id) throws EntityNotFoundException {
        return reviewRepo.findByCourseIdOrderByCreatedDesc(id);
    }
}
