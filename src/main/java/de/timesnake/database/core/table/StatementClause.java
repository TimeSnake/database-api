/*
 * workspace.database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
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
