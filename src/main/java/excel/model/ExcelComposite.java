package excel.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ExcelComposite<ParentType extends ExcelComposite<?>> extends ExcelComponent<ParentType> {
    private final List<ExcelComponent<?>> children;

    public ExcelComposite(final ParentType parent) {
        super(parent);
        this.children = new ArrayList<>();
    }

    public final void add(final ExcelComponent<?> child) {
        this.children.add(child);
    }

    public final ExcelComponent<?> findLastChild() {
        return this.children.get(this.children.size() - 1);
    }

    @Override
    public final void form() {
        this.formChildren();
        this.doAfterFormChildren();
    }

    protected void doAfterFormChildren() {

    }

    private void formChildren() {
        this.children.forEach(ExcelComponent::form);
    }
}
