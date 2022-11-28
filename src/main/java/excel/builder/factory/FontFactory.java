package excel.builder.factory;

import excel.builder.cellcreatingcontext.CellComponentCreatingContext;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public final class FontFactory {
    private static final String DEFAULT_FONT_NAME = "Arial";
    private static final Short DEFAULT_FONT_HEIGHT_IN_POINTS = 10;
    private static final Boolean DEFAULT_BOLD = false;

    public Font create(final Workbook workbook, final CellComponentCreatingContext<?> context) {
        final Font font = workbook.createFont();
        font.setFontName(findFontName(context));
        font.setFontHeightInPoints(findFontHeightInPoints(context));
        font.setBold(findBold(context));
        return font;
    }

    private static String findFontName(final CellComponentCreatingContext<?> context) {
        return context.getFontName() != null ? context.getFontName() : DEFAULT_FONT_NAME;
    }

    private static Short findFontHeightInPoints(final CellComponentCreatingContext<?> context) {
        return context.getFontHeightInPoints() != null ? context.getFontHeightInPoints() : DEFAULT_FONT_HEIGHT_IN_POINTS;
    }

    private static Boolean findBold(final CellComponentCreatingContext<?> context) {
        return context.getBold() != null ? context.getBold() : DEFAULT_BOLD;
    }
}
