package excel.builder.exception;

public final class NotDefinedCellCoordinateException extends RuntimeException {
    public NotDefinedCellCoordinateException() {
        super();
    }

    public NotDefinedCellCoordinateException(final String description) {
        super(description);
    }

    public NotDefinedCellCoordinateException(final Exception cause) {
        super(cause);
    }

    public NotDefinedCellCoordinateException(final String description, final Exception cause) {
        super(description, cause);
    }
}
