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

package de.timesnake.database.core.decoration;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbHead extends TableQuery implements de.timesnake.database.util.decoration.DbHead {

    protected DbHead(DatabaseConnector databaseConnector, String nameTable, String tag) {
        super(databaseConnector, nameTable, new Entry<>(tag, Column.Decoration.HEAD_TAG));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Decoration.HEAD_TAG) != null;
    }

    @Override
    public void delete() {
        super.deleteWithKey();
    }

    @NotNull
    @Override
    public String getTag() {
        return (String) super.primaryEntries.get(0).getValue();
    }

    @NotNull
    @Override
    public String getName() {
        return super.getFirstWithKey(Column.Decoration.HEAD_NAME);
    }

    @Override
    public void setName(String name) {
        super.setWithKey(name, Column.Decoration.HEAD_NAME);
    }

    @NotNull
    @Override
    public String getSection() {
        return super.getFirstWithKey(Column.Decoration.HEAD_SECTION);
    }

    @Override
    public void setSection(String section) {
        super.setWithKey(section, Column.Decoration.HEAD_SECTION);
    }
}
