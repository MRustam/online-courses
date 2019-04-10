package ru.rmamedov.courses.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rmamedov.courses.exception.EntityErrorResponse;
import ru.rmamedov.courses.exception.handler.helper.HandlerHelper;
import ru.rmamedov.courses.exception.user.UserNotFoundException;
import ru.rmamedov.courses.exception.user.UserNotSavedException;

@ControllerAdvice
public class GlobalUserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleUserNotFound(final UserNotFoundException ex) {
        return new ResponseEntity<>(HandlerHelper.notFound(new EntityErrorResponse<>(), ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleUserNotSaved(final UserNotSavedException ex) {
        return new ResponseEntity<>(HandlerHelper.notSaved(new EntityErrorResponse<>(), ex), HttpStatus.NOT_ACCEPTABLE);
    }
}
