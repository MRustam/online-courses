package ru.rmamedov.courses.exception.handler.helper;

import org.springframework.http.HttpStatus;
import ru.rmamedov.courses.exception.EntityErrorResponse;

/**
 * @author Rustam Mamedov
 */

public class HandlerHelper {

    public static EntityErrorResponse notFound(final EntityErrorResponse entityErrorResponse, final Exception ex) {
        entityErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        entityErrorResponse.setMessage(ex.getMessage());
        entityErrorResponse.setTimeStamp(System.currentTimeMillis());
        return entityErrorResponse;
    }
    public static EntityErrorResponse notSaved(final EntityErrorResponse entityErrorResponse, final Exception ex) {
        entityErrorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        entityErrorResponse.setMessage(ex.getMessage());
        entityErrorResponse.setTimeStamp(System.currentTimeMillis());
        return entityErrorResponse;
    }
    public static EntityErrorResponse badRequest(final EntityErrorResponse entityErrorResponse, final Exception ex) {
        entityErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        entityErrorResponse.setMessage(ex.getMessage());
        entityErrorResponse.setTimeStamp(System.currentTimeMillis());
        return entityErrorResponse;
    }
}
