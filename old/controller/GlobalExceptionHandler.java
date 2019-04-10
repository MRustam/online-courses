package ru.rmamedov.courses.springbootappcourses.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rmamedov.courses.springbootappcourses.exception.EntityErrorResponse;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotFoundException;
import ru.rmamedov.courses.springbootappcourses.exception.EntityNotSavedException;

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
    public ResponseEntity<EntityErrorResponse> handleNotSaved(EntityNotSavedException exc) {

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
