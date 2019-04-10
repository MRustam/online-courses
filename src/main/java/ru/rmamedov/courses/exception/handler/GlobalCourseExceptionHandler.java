package ru.rmamedov.courses.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rmamedov.courses.exception.EntityErrorResponse;
import ru.rmamedov.courses.exception.course.CourseNotFoundException;
import ru.rmamedov.courses.exception.course.CourseNotSavedException;
import ru.rmamedov.courses.exception.handler.helper.HandlerHelper;

@ControllerAdvice
public class GlobalCourseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleCourseNotFound(final CourseNotFoundException ex) {
        return new ResponseEntity<>(HandlerHelper.notFound(new EntityErrorResponse<>(), ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleCourseNotSaved(final CourseNotSavedException ex) {
        return new ResponseEntity<>(HandlerHelper.notSaved(new EntityErrorResponse<>(), ex), HttpStatus.NOT_ACCEPTABLE);
    }
}
