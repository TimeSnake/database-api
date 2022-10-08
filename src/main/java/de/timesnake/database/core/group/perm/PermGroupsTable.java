/*
 * database-api.main
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

package de.timesnake.database.core.group.perm;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.GroupBasisTable;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PermGroupsTable extends GroupBasisTable {

    public PermGroupsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName);
        super.addColumn(Column.Group.INHERITANCE);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    @NotNull
    public DbPermGroup getGroup(String name) {
        return new DbPermGroup(this.databaseConnector, name, this.tableName);
    }

    public Collection<DbPermGroup> getGroups() {
        List<DbPermGroup> groups = new ArrayList<>();
        for (String name : this.getGroupNames()) {
            groups.add(this.getGroup(name));
        }
        return groups;
    }
}
