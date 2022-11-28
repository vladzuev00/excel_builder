package excel.model.exception;

public final class NotValidPathException extends RuntimeException {
    public NotValidPathException() {
        super();
    }

    public NotValidPathException(final String description) {
        super(description);
    }

    public NotValidPathException(final Exception cause) {
        super(cause);
    }

    public NotValidPathException(final String description, final Exception cause) {
        super(description, cause);
    }
}
