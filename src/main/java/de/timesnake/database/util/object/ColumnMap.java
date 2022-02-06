package de.timesnake.database.util.object;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;

import java.util.HashMap;

public class ColumnMap extends HashMap<Column<?>, Object> {

    public <Value> void put(Column<Value> column, Value value) {
        super.put(column, value);
    }

    public void put(TableEntry<?> entry) {
        super.put(entry.getColumn(), entry.getValue());
    }

    public <Value> Value get(Column<Value> column) {
        return (Value) super.get(column);
    }
}
