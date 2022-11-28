package excel.model.cell;

import excel.model.SheetComponent;

import excel.model.cell.coordinate.CellComponentCoordinate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.function.BiConsumer;

public final class BooleanCellComponent extends AbstractCellComponent<Boolean> {

    public BooleanCellComponent(final SheetComponent parent, final CellComponentCoordinate coordinate,
                                final CellStyle cellStyle, final Boolean content) {
        super(parent, coordinate, cellStyle, content);
    }

    @Override
    protected BiConsumer<Cell, Boolean> contentSetter() {
        return Cell::setCellValue;
    }
}
