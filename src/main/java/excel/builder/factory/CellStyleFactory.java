package excel.builder.factory;

import excel.builder.cellcreatingcontext.CellComponentCreatingContext;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;

import static org.apache.poi.ss.usermodel.BorderStyle.THIN;
import static org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND;
import static org.apache.poi.ss.usermodel.HorizontalAlignment.LEFT;
import static org.apache.poi.ss.usermodel.IndexedColors.WHITE;

@RequiredArgsConstructor
public final class CellStyleFactory {
    private static final IndexedColors DEFAULT_BACKGROUND_COLOR = WHITE;
    private static final HorizontalAlignment DEFAULT_ALIGNMENT = LEFT;
    private static final BorderStyle DEFAULT_BORDER_STYLE = THIN;

    private final FontFactory fontFactory;

    public CellStyle create(final Workbook workbook, final CellComponentCreatingContext<?> context) {
        final CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillBackgroundColor(findBackgroundColor(context).index);
        cellStyle.setFillPattern(SOLID_FOREGROUND);
        cellStyle.setAlignment(findAlignment(context));
        setBorderStyle(cellStyle, findBorderStyle(context));
        this.setFont(cellStyle, workbook, context);
        return cellStyle;
    }

    private static IndexedColors findBackgroundColor(final CellComponentCreatingContext<?> context) {
        return context.getBackgroundColor() != null ? context.getBackgroundColor() : DEFAULT_BACKGROUND_COLOR;
    }

    private static HorizontalAlignment findAlignment(final CellComponentCreatingContext<?> context) {
        return context.getAlignment() != null ? context.getAlignment() : DEFAULT_ALIGNMENT;
    }

    private static BorderStyle findBorderStyle(final CellComponentCreatingContext<?> context) {
        return context.getBorderStyle() != null ? context.getBorderStyle() : DEFAULT_BORDER_STYLE;
    }

    private static void setBorderStyle(final CellStyle cellStyle, final BorderStyle borderStyle) {
        cellStyle.setBorderTop(borderStyle);
        cellStyle.setBorderRight(borderStyle);
        cellStyle.setBorderBottom(borderStyle);
        cellStyle.setBorderLeft(borderStyle);
    }

    private void setFont(final CellStyle cellStyle, final Workbook workbook,
                         final CellComponentCreatingContext<?> context) {
        final Font font = this.fontFactory.create(workbook, context);
        cellStyle.setFont(font);
    }
}
