package excel.model;

import org.apache.poi.ss.usermodel.Sheet;

public final class SheetComponent extends ExcelComposite<WorkbookComponent> {
    private final Sheet sheet;

    public SheetComponent(final WorkbookComponent parent, final String sheetName) {
        super(parent);
        this.sheet = parent.getWorkbook().createSheet(sheetName);
    }

    public Sheet getSheet() {
        return this.sheet;
    }
}
