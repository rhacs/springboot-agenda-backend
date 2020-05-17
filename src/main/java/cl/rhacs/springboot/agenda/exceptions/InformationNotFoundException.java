package cl.rhacs.springboot.agenda.exceptions;

public class InformationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6204322987249163402L;

    /**
     * Creates a new and empty {@link InformationNotFoundException}
     */
    public InformationNotFoundException() {
        super();
    }

    /**
     * Creates a new {@link InformationNotFoundException} given a message
     *
     * @param message the detail message
     */
    public InformationNotFoundException(final String message) {
        super(message);
    }

    /**
     * Creates a new {@link InformationNotFoundException} given a cause
     *
     * @param cause the cause
     */
    public InformationNotFoundException(final Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new {@link InformationNotFoundException} given a message and a
     * cause
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public InformationNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new {@link InformationNotFoundException} given a message, a cause,
     * should the suppression be enabled and should the stack trace be writable
     *
     * @param message            the detail message
     * @param cause              the cause
     * @param enableSuppression  whether or not the suppression is enabled or
     *                           disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public InformationNotFoundException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
