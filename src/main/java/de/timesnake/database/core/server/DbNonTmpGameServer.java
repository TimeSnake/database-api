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

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DbNonTmpGameServer extends DbPvPServer implements de.timesnake.database.util.server.DbNonTmpGameServer {

    public DbNonTmpGameServer(DatabaseConnector databaseConnector, Integer port, String nameTable) {
        super(databaseConnector, port, nameTable);
    }

    @Nullable
    @Override
    public String getGameInfo() {
        return super.getFirstWithKey(Column.Server.GAME_INFO);
    }

    @Override
    public void setGameInfo(String info) {
        super.setWithKey(info, Column.Server.GAME_INFO);
    }

    @Nullable
    @Override
    public UUID getOwnerUuid() {
        return super.getFirstWithKey(Column.Server.OWNER);
    }

    @Override
    public void setOwnerUuid(UUID uuid) {
        super.setWithKey(uuid, Column.Server.OWNER);
    }

    @Override
    public boolean hasOwner() {
        return this.getOwnerUuid() != null;
    }

    @NotNull
    @Override
    public Type.Server<de.timesnake.database.util.server.DbNonTmpGameServer> getType() {
        return Type.Server.GAME;
    }
}
