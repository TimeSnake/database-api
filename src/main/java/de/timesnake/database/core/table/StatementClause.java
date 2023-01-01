/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.table;

import de.timesnake.database.core.Entry;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static de.timesnake.database.core.table.Table.COLUMN_WRAPPER;

public abstract class StatementClause {

    protected final Collection<Entry<?>> entries;

    public StatementClause(Collection<Entry<?>> entries) {
        this.entries = entries;
    }

    public StatementClause(Entry<?>... entries) {
        this.entries = Arrays.asList(entries);
    }

    public abstract String getText();

    public int applyValues(PreparedStatement statement, int index) throws SQLException {
        for (Entry<?> entry : this.entries) {
            entry.applyOnStatement(statement, index);
            index++;
        }
        return index;
    }

    static String parseToEquation(Collection<Entry<?>> entries, String splitter) {
        return entries.stream()
                .map(e -> COLUMN_WRAPPER + e.getColumn().getName() + COLUMN_WRAPPER + " = " +
                          e.getColumn().getType().getValueWrapper())
                .collect(Collectors.joining(splitter));
    }
}
