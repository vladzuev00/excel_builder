package excel.builder.cellcreatingcontext;

import excel.model.cell.coordinate.CellComponentCoordinate;
import lombok.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public final class CellComponentCreatingContext<ContentType> {
    String fontName;
    Short fontHeightInPoints;
    Boolean bold;
    IndexedColors backgroundColor;
    HorizontalAlignment alignment;
    BorderStyle borderStyle;
    ContentType content;
    CellComponentCoordinate coordinate;
}
