package ru.rmamedov.courses.springbootappcourses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rmamedov.courses.springbootappcourses.model.Course;

@ControllerAdvice
public class CourseGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse<Course>> handleCourseNotFound(EntityNotFoundException exc) {

        EntityErrorResponse<Course> entityErrorResponse = new EntityErrorResponse<>();

        entityErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        entityErrorResponse.setMessage(exc.getMessage());
        entityErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(entityErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse<Course>> handleOtherExceptions(Exception exc) {

        EntityErrorResponse<Course> entityErrorResponse = new EntityErrorResponse<>();

        entityErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        entityErrorResponse.setMessage(exc.getMessage());
        entityErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(entityErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
