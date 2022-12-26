/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Entry<Value> {

    private final Column<Value> column;
    private final Value value;

    public Entry(Value value, Column<Value> column) {
        this.column = column;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public Column<Value> getColumn() {
        return column;
    }

    public void applyOnStatement(PreparedStatement statement, int index) throws SQLException {
        if (this.value != null) {
            this.column.getType().applyOnStatement(statement, index, this.value);
        } else {
            statement.setNull(index, Types.NULL);
        }
    }
}
