package cl.rhacs.springboot.agenda.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class CustomError {

    // Attributes
    // -----------------------------------------------------------------------------------------

    private final Date timestamp;

    private HttpStatus httpStatus;

    private String message;

    private final Map<String, String> errors;

    // Constructors
    // -----------------------------------------------------------------------------------------

    /**
     * Creates a new {@link CustomError} with the timestamp initialized
     */
    public CustomError() {
        timestamp = new Date();
        errors = new HashMap<>();
    }

    /**
     * Creates a new {@link CustomError} given a {@link HttpStatus}
     *
     * @param httpStatus the http status to set
     */
    public CustomError(final HttpStatus httpStatus) {
        this();
        this.httpStatus = httpStatus;
    }

    /**
     * Creates a new {@link CustomError} given a {@link HttpStatus} and a
     * {@link Exception}
     *
     * @param httpStatus the http status to set
     * @param exception  the exception to set
     */
    public CustomError(final HttpStatus httpStatus, final Exception exception) {
        this(httpStatus);

        final String aux = exception.getLocalizedMessage();
        this.message = (aux == null) ? exception.getMessage() : aux;
    }

    // Methods
    // -----------------------------------------------------------------------------------------

    /**
     * Adds an error to the error list given a field and a message
     *
     * @param field   the name of the field
     * @param message the error message
     */
    private void addError(final String field, final String message) {
        errors.put(field, message);
    }

    /**
     * Adds a {@link FieldError} to the error list
     *
     * @param fieldError the field error to add
     */
    private void addError(final FieldError fieldError) {
        addError(fieldError.getObjectName(), fieldError.getDefaultMessage());
    }

    /**
     * Adds a {@link ObjectError} to the error list
     *
     * @param objectError the object error to add
     */
    @SuppressWarnings(value = { "unused" })
    private void addError(final ObjectError objectError) {
        addError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    /**
     * Adds all the {@link FieldError}s to the error list
     *
     * @param fieldErrors the list of field errors to add
     */
    @SuppressWarnings(value = { "unused" })
    private void addError(final List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addError);
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @return the httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the errors
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param httpStatus the httpStatus to set
     */
    public void setHttpStatus(final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    // Inheritances (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "CustomError [timestamp=" + timestamp + ", httpStatus=" + httpStatus + ", message=" + message
                + ", errors=" + errors + "]";
    }

}
