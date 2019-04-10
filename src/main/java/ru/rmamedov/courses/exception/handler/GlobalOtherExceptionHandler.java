package ru.rmamedov.courses.exception.handler;

        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import ru.rmamedov.courses.exception.EntityErrorResponse;
        import ru.rmamedov.courses.exception.handler.helper.HandlerHelper;

@ControllerAdvice
public class GlobalOtherExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleOtherExceptions(final Exception ex) {
        return new ResponseEntity<>(HandlerHelper.badRequest(new EntityErrorResponse(), ex), HttpStatus.BAD_REQUEST);
    }
}
