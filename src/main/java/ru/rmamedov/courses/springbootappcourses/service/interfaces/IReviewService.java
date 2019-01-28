package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Review;

import java.util.List;

public interface IReviewService extends BaseInterface<Review, Long> {

    List<Review> findAllByCourseId(Long id) throws EntityNotFoundException;

}
