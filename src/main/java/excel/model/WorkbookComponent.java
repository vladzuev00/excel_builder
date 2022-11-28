package excel.model;

import excel.model.exception.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public final class WorkbookComponent extends ExcelComposite<WorkbookComponent> implements AutoCloseable {
    private static final String REGEX_FILE_PATH = ".+\\.xlsx";

    private final Workbook workbook;
    private final String filePath;

    public WorkbookComponent(final String filePath) {
        super(null);
        if (!filePath.matches(REGEX_FILE_PATH)) {
            throw new NotValidPathException("Given path '" + filePath + "' isn't valid.");
        }
        this.workbook = new XSSFWorkbook();
        this.filePath = filePath;
    }

    @Override
    public void close() {
        try {
            this.workbook.close();
        } catch (final IOException cause) {
            throw new ExcelComponentClosingException(cause);
        }
    }

    public Workbook getWorkbook() {
        return this.workbook;
    }

    @Override
    protected void doAfterFormChildren() {
        try (final OutputStream outputStream = new FileOutputStream(this.filePath, true)) {
            this.workbook.write(outputStream);
        } catch (final IOException cause) {
            throw new ExcelComponentFormingException(cause);
        }
    }
}
