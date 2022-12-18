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

package de.timesnake.database.core.server;

import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.database.util.server.DbTmpGameServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbLoungeServer extends DbTaskServer implements de.timesnake.database.util.server.DbLoungeServer {

    public DbLoungeServer(DatabaseConnector databaseConnector, String name, String nameTable) {
        super(databaseConnector, name, nameTable);
    }

    @Nullable
    @Override
    public DbTmpGameServer getTwinServer() {
        ServerTable<? extends DbServer> table = ((DatabaseServers) Database.getServers()).getServerTables().get(Type.Server.TEMP_GAME);
        if (table != null) {
            return ((TmpGameTable) table).getServerByTwinServerName(this.getName());
        }
        return null;
    }

    @NotNull
    @Override
    public Type.Server<de.timesnake.database.util.server.DbLoungeServer> getType() {
        return Type.Server.LOUNGE;
    }
}
