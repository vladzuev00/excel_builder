package excel.builder.exception;

public final class CellComponentCreatingException extends RuntimeException {
    public CellComponentCreatingException() {
        super();
    }

    public CellComponentCreatingException(final String description) {
        super(description);
    }

    public CellComponentCreatingException(final Exception cause) {
        super(cause);
    }

    public CellComponentCreatingException(final String description, final Exception cause) {
        super(description, cause);
    }
}
