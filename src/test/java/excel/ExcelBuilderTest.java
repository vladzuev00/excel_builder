package excel;

import excel.builder.ExcelBuilder;
import excel.builder.factory.CellStyleFactory;
import excel.builder.factory.FontFactory;
import excel.model.exception.NotValidPathException;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.util.function.Function;

public final class ExcelBuilderTest {
    private static final String FILE_PATH = "./src/test/resources/test.xlsx";
    private static final Function<String, ExcelBuilder> FUNCTION_FILE_PATH_TO_EXCEL_BUILDER
            = filePath -> new ExcelBuilder(filePath, new CellStyleFactory(new FontFactory()));

    @After
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteFile() {
        final File file = new File(FILE_PATH);
        file.delete();
    }

    @Test
    public void builderShouldBeCreated() {
        final ExcelBuilder excelBuilder = FUNCTION_FILE_PATH_TO_EXCEL_BUILDER.apply(FILE_PATH);
        excelBuilder.close();
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void builderShouldBeCreatedByPathExistFile() throws Exception {
        final File file = new File(FILE_PATH);
        file.createNewFile();
        final ExcelBuilder excelBuilder = FUNCTION_FILE_PATH_TO_EXCEL_BUILDER.apply(FILE_PATH);
        excelBuilder.close();
    }

    @Test(expected = NotValidPathException.class)
    public void builderShouldNotBeCreatedByPathWithoutXLSXPrefix() {
        final String filePath = "./src/test/resources/test.xls";
        final ExcelBuilder excelBuilder = FUNCTION_FILE_PATH_TO_EXCEL_BUILDER.apply(filePath);
        excelBuilder.close();
    }

    @Test
    public void sheetShouldBeCreated() {
        try (final ExcelBuilder excelBuilder = FUNCTION_FILE_PATH_TO_EXCEL_BUILDER.apply(FILE_PATH)) {
            excelBuilder.sheet("sheet");
        }
    }
}
