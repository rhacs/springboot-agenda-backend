package cl.rhacs.springboot.agenda.exceptions;

public class ContactNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6584983661875152093L;

    /**
     * Creates a new and empty {@link ContactNotFoundException}
     */
    public ContactNotFoundException() {
        super();
    }

    /**
     * Creates a new {@link ContactNotFoundException} given a message
     *
     * @param message the detail message
     */
    public ContactNotFoundException(final String message) {
        super(message);
    }

    /**
     * Creates a new {@link ContactNotFoundException} given a cause
     * ({@link Throwable})
     *
     * @param cause the cause
     */
    public ContactNotFoundException(final Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new {@link ContactNotFoundException} given a message and a cause
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public ContactNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new {@link ContactNotFoundException} given a message, a cause, if
     * it should be supressed and if whether or not the stacktrace should be
     * writable
     *
     * @param message            the detail message
     * @param cause              the cause
     * @param enableSuppression  whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public ContactNotFoundException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
