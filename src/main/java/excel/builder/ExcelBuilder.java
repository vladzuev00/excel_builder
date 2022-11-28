package excel.builder;

import excel.builder.cellcreatingcontext.CellComponentCreatingContext;
import excel.builder.exception.CellComponentCreatingException;
import excel.builder.exception.NotDefinedCellCoordinateException;
import excel.builder.factory.CellStyleFactory;
import excel.model.cell.*;
import excel.model.SheetComponent;
import excel.model.WorkbookComponent;
import excel.model.cell.coordinate.CellComponentCoordinate;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.reflect.Constructor;
import java.time.LocalDateTime;

public final class ExcelBuilder implements AutoCloseable {
    private final WorkbookComponent workbookComponent;
    private final CellStyleFactory cellStyleFactory;

    public ExcelBuilder(final String filePath, final CellStyleFactory cellStyleFactory) {
        this.workbookComponent = new WorkbookComponent(filePath);
        this.cellStyleFactory = cellStyleFactory;
    }

    public ExcelBuilder sheet(final String name) {
        new SheetComponent(this.workbookComponent, name);
        return this;
    }

    public ExcelBuilder textCell(final CellComponentCreatingContext<String> context) {
        this.createCellComponent(TextCellComponent.class, String.class, context);
        return this;
    }

    public ExcelBuilder numberCell(final CellComponentCreatingContext<Double> context) {
        this.createCellComponent(NumberCellComponent.class, Double.class, context);
        return this;
    }

    public ExcelBuilder booleanCell(final CellComponentCreatingContext<Boolean> context) {
        this.createCellComponent(BooleanCellComponent.class, Boolean.class, context);
        return this;
    }

    public ExcelBuilder dateTimeCell(final CellComponentCreatingContext<LocalDateTime> context) {
        this.createCellComponent(DateTimeCellComponent.class, LocalDateTime.class, context);
        return this;
    }

    public void build() {
        this.workbookComponent.form();
    }

    @Override
    public void close() {
        this.workbookComponent.close();
    }

    private <ContentType, CellComponentType extends AbstractCellComponent<ContentType>> void createCellComponent(
            final Class<CellComponentType> cellComponentType, final Class<ContentType> contentType,
            final CellComponentCreatingContext<ContentType> context) {
        try {
            final CellStyle cellStyle = this.cellStyleFactory.create(this.workbookComponent.getWorkbook(), context);
            final Constructor<CellComponentType> cellComponentConstructor = cellComponentType.getConstructor(
                    SheetComponent.class, CellComponentCoordinate.class, CellStyle.class, contentType);
            cellComponentConstructor.newInstance(this.findCurrentSheetComponent(), findCoordinate(context), cellStyle,
                    context.getContent());
        } catch (final Exception cause) {
            throw new CellComponentCreatingException(cause);
        }
    }

    private SheetComponent findCurrentSheetComponent() {
        return (SheetComponent) this.workbookComponent.findLastChild();
    }

    private static CellComponentCoordinate findCoordinate(final CellComponentCreatingContext<?> context) {
        if (context.getCoordinate() == null) {
            throw new NotDefinedCellCoordinateException();
        }
        return context.getCoordinate();
    }
}
