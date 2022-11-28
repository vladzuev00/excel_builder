package excel.model.cell;

import excel.model.SheetComponent;
import excel.model.cell.coordinate.CellComponentCoordinate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.time.LocalDateTime;
import java.util.function.BiConsumer;

public final class DateTimeCellComponent extends AbstractCellComponent<LocalDateTime> {

    public DateTimeCellComponent(final SheetComponent parent, final CellComponentCoordinate coordinate,
                                 final CellStyle cellStyle, final LocalDateTime content) {
        super(parent, coordinate, cellStyle, content);
    }

    @Override
    protected BiConsumer<Cell, LocalDateTime> contentSetter() {
        return Cell::setCellValue;
    }
}
