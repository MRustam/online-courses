package ru.rmamedov.courses.exception.exceptions.course;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class CourseFailToSaveException extends RuntimeException {

    public CourseFailToSaveException(String message) {
        super(message);
    }
}
