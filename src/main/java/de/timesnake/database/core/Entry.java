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
