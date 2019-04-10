package ru.rmamedov.courses.springbootappcourses.service.interfaces;

import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotSavedException;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.model.User;

import java.util.List;

public interface IReviewService extends BaseInterface<Review, Long> {

    List<Review> findAllByCourseId(Long id) throws EntityNotFoundException;

    Review add(Long courseId, Review review, User user) throws EntityNotSavedException;

}
