package ru.rmamedov.courses.springbootappcourses.exception;

public class EntityNotSaved extends RuntimeException {

    public EntityNotSaved(String message) {
        super(message);
    }
}
