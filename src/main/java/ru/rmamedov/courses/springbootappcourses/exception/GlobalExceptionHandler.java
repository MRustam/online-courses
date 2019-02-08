package ru.rmamedov.courses.springbootappcourses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleNotFound(EntityNotFoundException exc) {

        EntityErrorResponse entityErrorResponse = new EntityErrorResponse<>();

        entityErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        entityErrorResponse.setMessage(exc.getMessage());
        entityErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(entityErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleNotSaved(EntityNotSaved exc) {

        EntityErrorResponse entityErrorResponse = new EntityErrorResponse<>();

        entityErrorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        entityErrorResponse.setMessage(exc.getMessage());
        entityErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(entityErrorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleOtherExceptions(Exception exc) {

        EntityErrorResponse entityErrorResponse = new EntityErrorResponse<>();

        entityErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        entityErrorResponse.setMessage(exc.getMessage());
        entityErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(entityErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
