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

package de.timesnake.database.core.endgame;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbNonTmpGameServer;

import java.util.UUID;

public class DbEndGameUser extends TableQuery implements de.timesnake.database.util.endgame.DbEndGameUser {

    protected DbEndGameUser(DatabaseConnector databaseConnector, String nameTable, UUID uuid) {
        super(databaseConnector, nameTable, new TableEntry<>(uuid, Column.EndGame.PLAYER_UUID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.EndGame.PLAYER_UUID) != null;
    }

    @Override
    public void delete() {
        super.deleteWithKey();
    }

    @Override
    public UUID getUniqueId() {
        return (UUID) super.primaryEntries.get(0).getValue();
    }

    @Override
    public String getName() {
        return super.getFirstWithKey(Column.EndGame.PLAYER_NAME);
    }

    @Override
    public DbNonTmpGameServer getServer() {
        String serverName = super.getFirstWithKey(Column.EndGame.SERVER);
        return serverName == null ? null : Database.getServers().getServer(serverName);
    }

    @Override
    public void setServer(DbNonTmpGameServer server) {
        super.setWithKey(server.getName(), Column.EndGame.SERVER);
    }
}
