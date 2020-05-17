package cl.rhacs.springboot.agenda.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.rhacs.springboot.agenda.models.Contact;
import cl.rhacs.springboot.agenda.models.CustomError;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles {@link ContactNotFoundException}
     *
     * @param exception ContactNotFoundException
     * @param request   WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { ContactNotFoundException.class })
    public ResponseEntity<CustomError> handleContactNotFoundException(final ContactNotFoundException exception,
            final WebRequest request) {
        final CustomError customError = new CustomError(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(customError, customError.getHttpStatus());
    }

    /**
     * Handles {@link InformationNotFoundException} when the user tries to look for
     * a phone number, tag or note in a {@link Contact} that doesn't have them
     *
     * @param exception InformationNotFoundException
     * @param request   WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { InformationNotFoundException.class })
    public ResponseEntity<CustomError> handleInformationNotFoundException(final InformationNotFoundException exception,
            final WebRequest request) {
        final CustomError customError = new CustomError(HttpStatus.NO_CONTENT, exception);
        return new ResponseEntity<>(customError, customError.getHttpStatus());
    }

    /**
     * Handles the {@link NoHandlerFoundException} when the user tries to access a
     * non defined context path
     *
     * @param ex      NoHandlerFoundException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final CustomError customError = new CustomError(HttpStatus.BAD_REQUEST);
        customError.setMessage(
                String.format("Could not find the %s method for the URL %s", ex.getHttpMethod(), ex.getRequestURL()));

        return buildResponse(customError);
    }

    /**
     * Handles {@link Exception}
     *
     * @param exception Exception
     * @param headers   HttpHeaders
     * @param status    HttpStatus
     * @param request   WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleGlobalException(final Exception exception, final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        final CustomError customError = new CustomError(HttpStatus.I_AM_A_TEAPOT, exception);
        return buildResponse(customError);
    }

    /**
     * Builds the actual {@link ResponseEntity} to dispatch
     *
     * @param customError the error
     * @return ResponseEntity
     */
    private ResponseEntity<Object> buildResponse(final CustomError customError) {
        return new ResponseEntity<>(customError, customError.getHttpStatus());
    }

}
