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

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;

public class DbLobbyServer extends DbServer implements de.timesnake.database.util.server.DbLobbyServer {

    public DbLobbyServer(DatabaseConnector databaseConnector, String name, String nameTable) {
        super(databaseConnector, name, nameTable);
    }

    @NotNull
    @Override
    public Type.Server<de.timesnake.database.util.server.DbLobbyServer> getType() {
        return Type.Server.LOBBY;
    }
}
