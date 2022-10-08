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

package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

public class LoungeTable extends TaskTable<DbLoungeServer> {

    public LoungeTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
    }

    @Override
    public void backup() {
        this.dropTmpTable();
    }

    @Nullable
    @Override
    public DbLoungeServer getServer(int port) {
        DbLoungeServer server = new DbLoungeServer(this.databaseConnector, port, this.tableName);
        return server.exists() ? server : null;
    }
}
