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

import java.util.Collection;
import java.util.stream.Collectors;

import static de.timesnake.database.core.table.Table.COLUMN_WRAPPER;

public class EquationClause extends StatementClause {

    public EquationClause(Collection<Entry<?>> entries) {
        super(entries);
    }

    public EquationClause(Entry<?>... entries) {
        super(entries);
    }

    @Override
    public String getText() {
        return entries.stream()
                .map(e -> COLUMN_WRAPPER + e.getColumn().getName() + COLUMN_WRAPPER + " = " +
                          e.getColumn().getType().getValueWrapper())
                .collect(Collectors.joining(", "));
    }
}
