package ru.rmamedov.courses.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class UserNotSavedException extends RuntimeException {

    public UserNotSavedException(String message) {
        super(message);
    }
}
