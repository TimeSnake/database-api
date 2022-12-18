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

package de.timesnake.database.core.permisson;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DbPermission extends TableQuery implements de.timesnake.database.util.permission.DbPermission {

    public DbPermission(DatabaseConnector databaseConnector, int id, String nameTable) {
        super(databaseConnector, nameTable, new Entry<>(id, Column.Permission.ID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Permission.ID) != null;
    }

    @NotNull
    @Override
    public Integer getId() {
        return (Integer) super.primaryEntries.get(0).getValue();
    }

    @NotNull
    @Override
    public String getName() {
        return super.getFirstWithKey(Column.Permission.PERMISSION);
    }

    @Override
    public void setName(String name) {
        super.setWithKey(name, Column.Permission.PERMISSION);
    }

    @NotNull
    @Override
    public Status.Permission getMode() {
        return super.getFirstWithKey(Column.Permission.MODE);
    }

    @Override
    public void setMode(Status.Permission mode) {
        super.setWithKey(mode, Column.Permission.MODE);
    }

    @Nullable
    @Override
    public Collection<String> getServers() {
        return super.getFirstWithKey(Column.Permission.SERVER);
    }

    @Override
    public void setServers(Collection<String> servers) {
        super.setWithKey(servers != null ? new DbStringArrayList(servers) : null, Column.Permission.SERVER);
    }


}
