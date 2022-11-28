package excel.model.exception;

public final class ExcelComponentClosingException extends RuntimeException {
    public ExcelComponentClosingException() {
        super();
    }

    public ExcelComponentClosingException(final String description) {
        super(description);
    }

    public ExcelComponentClosingException(final Exception cause) {
        super(cause);
    }

    public ExcelComponentClosingException(final String description, final Exception cause) {
        super(description, cause);
    }
}
