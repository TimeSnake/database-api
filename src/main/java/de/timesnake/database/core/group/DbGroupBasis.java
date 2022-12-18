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

package de.timesnake.database.core.group;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbGroupBasis extends TableQuery implements de.timesnake.database.util.group.DbGroupBasis {

    protected DbGroupBasis(DatabaseConnector databaseConnector, String name, String nameTable) {
        super(databaseConnector, nameTable, new Entry<>(name, Column.Group.NAME));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Group.PRIORITY) != null;
    }

    @NotNull
    @Override
    public String getName() {
        return ((String) this.primaryEntries.get(0).getValue());
    }

    @Override
    public void setName(String name) {
        super.setWithKey(name, Column.Group.NAME);
    }

    @NotNull
    @Override
    public Integer getRank() {
        return super.getFirstWithKey(Column.Group.PRIORITY);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroupBasis toLocal() {
        return new DbCachedGroupBasis(this);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroupBasis toDatabase() {
        return this;
    }

}
