package excel;

import excel.builder.ExcelBuilder;
import excel.builder.cellcreatingcontext.CellComponentCreatingContext;
import excel.builder.factory.CellStyleFactory;
import excel.builder.factory.FontFactory;
import excel.model.cell.coordinate.CellComponentCoordinate;

import static org.apache.poi.ss.usermodel.BorderStyle.MEDIUM;
import static org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER;
import static org.apache.poi.ss.usermodel.IndexedColors.YELLOW;

public final class Runner {
    public static void main(final String... args) {
        try (final ExcelBuilder excelBuilder = new ExcelBuilder("./src/main/resources/book1.xlsx",
                new CellStyleFactory(new FontFactory()))) {
            excelBuilder
                    .sheet("sheet")

                    .textCell(CellComponentCreatingContext.<String>builder()
                            .fontName("Arial")
                            .fontHeightInPoints((short) 10)
                            .bold(true)
                            .backgroundColor(YELLOW)
                            .alignment(CENTER)
                            .borderStyle(MEDIUM)
                            .content("Автомобиль, прицеп, полуприцеп")
                            .coordinate(new CellComponentCoordinate(1, 1, 1, 15))
                            .build())

                    .textCell(CellComponentCreatingContext.<String>builder()
                            .fontName("Arial")
                            .fontHeightInPoints((short) 10)
                            .bold(false)
                            .backgroundColor(YELLOW)
                            .alignment(CENTER)
                            .borderStyle(MEDIUM)
                            .content("Марка автомобиля, прицепа, полуприцепа")
                            .coordinate(new CellComponentCoordinate(2, 1, 4, 5))
                            .build())

                    .build();
        }
    }
}
