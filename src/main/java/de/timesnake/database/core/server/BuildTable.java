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

public class BuildTable extends TaskTable<DbBuildServer> {

    private final BuildWorldTable buildWorldTable;

    public BuildTable(DatabaseConnector databaseConnector, String nameTable, BuildWorldTable buildWorldTable) {
        super(databaseConnector, nameTable);
        this.buildWorldTable = buildWorldTable;
    }

    @Nullable
    @Override
    public DbBuildServer getServer(int port) {
        DbBuildServer server = new DbBuildServer(this.databaseConnector, port, this.tableName, this.buildWorldTable);
        return server.exists() ? server : null;
    }

    @Override
    public void backup() {
        super.dropTmpTable();
    }
}
