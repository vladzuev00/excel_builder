package excel.model.cell.coordinate;

import lombok.Value;

@Value
public class CellComponentCoordinate {
    ExcelCellCoordinate leftUpperCoordinate;
    ExcelCellCoordinate rightBottomCoordinate;

    public CellComponentCoordinate(final int leftUpperRowIndex, final int leftUpperColumnIndex,
                                   final int rightBottomRowIndex, final int rightBottomColumnIndex) {
        this.leftUpperCoordinate = new ExcelCellCoordinate(leftUpperRowIndex, leftUpperColumnIndex);
        this.rightBottomCoordinate = new ExcelCellCoordinate(rightBottomRowIndex, rightBottomColumnIndex);
    }
}
