package de.timesnake.database.core;

public class TableEntry<Value> {

    private final Value value;
    private final Column<Value> column;

    public TableEntry(Value value, Column<Value> column) {
        this.column = column;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public Column<Value> getColumn() {
        return column;
    }
}
