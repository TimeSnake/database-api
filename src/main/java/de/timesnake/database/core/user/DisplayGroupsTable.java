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

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class DisplayGroupsTable extends TableDDL {

    public DisplayGroupsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.User.UUID, Column.User.DISPLAY_GROUP);
    }

    @Override
    protected void create() {
        super.create();
    }

    @Override
    protected void backup() {
        super.backup();
    }

    @NotNull
    public DbDisplayGroupUser getUser(UUID uuid) {
        return new DbDisplayGroupUser(this.databaseConnector, this.tableName, uuid);
    }
}
