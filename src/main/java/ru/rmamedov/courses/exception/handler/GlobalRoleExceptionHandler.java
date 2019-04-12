package ru.rmamedov.courses.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rmamedov.courses.exception.EntityErrorResponse;
import ru.rmamedov.courses.exception.exceptions.role.RoleAlreadyExistsException;
import ru.rmamedov.courses.exception.exceptions.role.RoleNotFoundException;
import ru.rmamedov.courses.exception.handler.helper.HandlerHelper;

@ControllerAdvice
public class GlobalRoleExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleUserNotFound(final RoleNotFoundException ex) {
        return new ResponseEntity<>(HandlerHelper.notFound(new EntityErrorResponse<>(), ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleUserNotSaved(final RoleAlreadyExistsException ex) {
        return new ResponseEntity<>(HandlerHelper.notSaved(new EntityErrorResponse<>(), ex), HttpStatus.NOT_ACCEPTABLE);
    }
}
