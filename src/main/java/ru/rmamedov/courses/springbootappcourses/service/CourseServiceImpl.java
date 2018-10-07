package ru.rmamedov.courses.springbootappcourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.repository.CourseRep;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.ICourseService;

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
        return courseRep.save(course);
    }

    @Override
    public void deleteOneById(Long id) {
        courseRep.delete(findOneById(id));
    }

    @Override
    public Course updateOneById(Long id, Course course) {

        deleteOneById(id);
//        Course c = new Course();
//        c.setId(id);
//        c.setTitle(course.getTitle());
//        c.setCategory(course.getCategory());
//        c.setDescription(course.getDescription());
//        c.setDuration(course.getDuration());
//        c.setStartDate(course.getStartDate());
//        c.setReview(course.getReview());
        course.setId(id);

        return saveOne(course);
    }
}
