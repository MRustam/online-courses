package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.repository.CourseRep;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.ICourseService;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private CourseRep courseRep;

    @Autowired
    public CourseServiceImpl(CourseRep courseRep) {
        this.courseRep = courseRep;
    }

    @Override
    public List<Course> findAll() {
        return courseRep.findAll();
    }

    @Override
    public Course findOneById(Long id) {
        Course course = courseRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id: '" + id + "' - Not found!"));
        return course;
    }

    @Override
    public Course saveOne(Course course) {
        if (course.getTitle() == null) {
            throw new EntityNotFoundException("Saving course is null!");
        }

//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        String firstName = userDetails.getPassword();
//
//        Instructor instructor = instructorService.findByFirstName(firstName);
//
//        course.setInstructor(instructor);

        return courseRep.save(course);
    }

    @Override
    public void deleteOneById(Long id) {
        courseRep.delete(findOneById(id));
    }

    @Override
    public Course updateOne(Course course) {
        return saveOne(course);
    }

    @Override
    public List<Course> getAllByRating() {
        return courseRep.findTop10ByOrderByRatingDesc();
    }

    @Override
    public List<Course> findOneByTitle(String title) {
        return courseRep.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Course> findByCategory(String category) {
        return courseRep.findByCategoryOrderByRatingDesc(category);
    }

    @Override
    public List<Review> getReviewsOfCurrentCourse(Long id) {
        return findOneById(id).getReviews();
    }
}
