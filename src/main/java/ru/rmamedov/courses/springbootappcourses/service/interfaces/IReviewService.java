package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.model.Review;

import java.util.List;

public interface IReviewService extends BaseInterface<Review, Long> {

    @Override
    List<Review> findAll();

    @Override
    Review findOneById(Long id);

    @Override
    Review saveOne(Review review);

    @Override
    void deleteOneById(Long id);

    @Override
    Review updateOneById(Long id, Review review);

    //Get course's reviews by id.
    List<Review> getReviewsOfCourse(Long id);
}
