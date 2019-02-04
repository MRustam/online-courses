package ru.rmamedov.courses.springbootappcourses.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.rmamedov.courses.springbootappcourses.model.Course;
import ru.rmamedov.courses.springbootappcourses.model.Instructor;
import ru.rmamedov.courses.springbootappcourses.model.Review;
import ru.rmamedov.courses.springbootappcourses.model.User;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.ICourseService;
import ru.rmamedov.courses.springbootappcourses.service.interfaces.IInstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private ICourseService iCourseService;
    private IInstructorService instructorService;

    @Autowired
    public CourseController(ICourseService iCourseService, IInstructorService instructorService) {
        this.iCourseService = iCourseService;
        this.instructorService = instructorService;
    }

    // CRUD operations

    // Custom get all courses, sorted by rating.
    @GetMapping("/all")
    public List<Course> getAll() {
        return iCourseService.getAllByRating();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Course> getOneById(@PathVariable Long id) {
        Course course = iCourseService.findById(id);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Course> saveOne(@RequestBody Course course, @AuthenticationPrincipal User user) {
        if (course == null || user == null) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        } else {

            Instructor instructor = instructorService.findByUsername(user.getUsername());
            if (instructor != null) {
                instructor.addCourse(course);
                return new ResponseEntity<>(iCourseService.save(course), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOneById(@PathVariable Long id) {
        iCourseService.deleteOneById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Course> updateById(@RequestBody Course course) {
        if (course == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(iCourseService.update(course), HttpStatus.OK);
    }

    //Find one by title.
    @GetMapping("/bytitle/{title}")
    public List<Course> findOneByTitle(@PathVariable String title) {
        return iCourseService.findOneByTitle(title);
    }

    //Filter by category.
    @GetMapping("/bycategory/{category}")
    public List<Course> findAllByCategory(@PathVariable String category) {
        return iCourseService.findByCategory(category);
    }
}
