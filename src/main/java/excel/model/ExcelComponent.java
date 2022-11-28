package excel.model;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public abstract class ExcelComponent<ParentType extends ExcelComposite<?>> {
    private final ParentType parent;

    public ExcelComponent(final ParentType parent) {
        this.parent = parent;
        if (this.parent != null) {
            this.parent.add(this);
        }
    }

    /**
     * Root's parent is null
     */
    public final Optional<ParentType> getParent() {
        return ofNullable(this.parent);
    }

    public abstract void form();
}
