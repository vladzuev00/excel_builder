package excel.model.exception;

public final class ExcelComponentFormingException extends RuntimeException {
    public ExcelComponentFormingException() {
        super();
    }

    public ExcelComponentFormingException(final String description) {
        super(description);
    }

    public ExcelComponentFormingException(final Exception cause) {
        super(cause);
    }

    public ExcelComponentFormingException(final String description, final Exception cause) {
        super(description, cause);
    }
}
