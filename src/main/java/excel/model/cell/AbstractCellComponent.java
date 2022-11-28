package excel.model.cell;

import excel.model.ExcelComponent;
import excel.model.SheetComponent;
import excel.model.cell.coordinate.CellComponentCoordinate;
import excel.model.cell.coordinate.ExcelCellCoordinate;
import excel.model.exception.ExcelComponentFormingException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.function.BiConsumer;

public abstract class AbstractCellComponent<ContentType> extends ExcelComponent<SheetComponent> {
    private final CellComponentCoordinate coordinate;
    private final CellStyle cellStyle;
    private final ContentType content;

    public AbstractCellComponent(final SheetComponent parent, final CellComponentCoordinate coordinate,
                                 final CellStyle cellStyle, final ContentType content) {
        super(parent);
        this.coordinate = coordinate;
        this.cellStyle = cellStyle;
        this.content = content;
    }

    @Override
    public final void form() {
        final Sheet sheet = this.findSheet();
        this.createCells(sheet);
        this.mergeCells(sheet);
        final Cell leftUpperCell = this.findLeftUpperCell(sheet);
        this.addContent(leftUpperCell);
        this.applyCellStyle(leftUpperCell);
    }

    protected abstract BiConsumer<Cell, ContentType> contentSetter();

    private Sheet findSheet() {
        return super.getParent()
                .map(SheetComponent::getSheet)
                .orElseThrow(ExcelComponentFormingException::new);
    }

    private void createCells(final Sheet sheet) {
        final ExcelCellCoordinate leftUpperCoordinate = this.coordinate.getLeftUpperCoordinate();
        final ExcelCellCoordinate rightBottomCoordinate = this.coordinate.getRightBottomCoordinate();
        for (int i = leftUpperCoordinate.getRowIndex(); i <= rightBottomCoordinate.getRowIndex(); i++) {
            for (int j = leftUpperCoordinate.getColumnIndex(); j <= rightBottomCoordinate.getColumnIndex(); j++) {
                createCell(sheet, i, j);
            }
        }
    }

    private static void createCell(final Sheet sheet, final int rowIndex, final int columnIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        row.createCell(columnIndex);
    }

    private void mergeCells(final Sheet sheet) {
        final ExcelCellCoordinate leftUpperCoordinate = this.coordinate.getLeftUpperCoordinate();
        final ExcelCellCoordinate rightBottomCoordinate = this.coordinate.getRightBottomCoordinate();
        sheet.addMergedRegion(
                new CellRangeAddress(
                        leftUpperCoordinate.getRowIndex(), rightBottomCoordinate.getRowIndex(),
                        leftUpperCoordinate.getColumnIndex(), rightBottomCoordinate.getColumnIndex()));
    }

    private Cell findLeftUpperCell(final Sheet sheet) {
        return sheet
                .getRow(this.coordinate.getLeftUpperCoordinate().getRowIndex())
                .getCell(this.coordinate.getLeftUpperCoordinate().getColumnIndex());
    }

    private void addContent(final Cell cell) {
        if (this.content != null) {
            this.contentSetter().accept(cell, this.content);
        }
    }

    private void applyCellStyle(final Cell cell) {
        cell.setCellStyle(this.cellStyle);
    }
}
