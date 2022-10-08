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
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbNonTmpGameServer;

import java.util.UUID;

public class EndGamesTable extends TableDDL {

    protected EndGamesTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.EndGame.PLAYER_UUID);
        super.addColumn(Column.EndGame.PLAYER_NAME);
        super.addColumn(Column.EndGame.SERVER);
    }

    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public de.timesnake.database.util.endgame.DbEndGameUser getUser(UUID uuid) {
        return new DbEndGameUser(this.databaseConnector, this.tableName, uuid);
    }

    public boolean containsUser(UUID uuid) {
        return super.getFirst(Column.EndGame.PLAYER_NAME) != null;
    }

    public void addUser(UUID uuid, String name, DbNonTmpGameServer server) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(uuid, Column.EndGame.PLAYER_UUID)), new TableEntry<>(name,
                Column.EndGame.PLAYER_NAME), new TableEntry<>(server.getName(), Column.EndGame.SERVER));
    }

    public void removeUser(UUID uuid) {
        super.deleteEntry(new TableEntry<>(uuid, Column.EndGame.PLAYER_UUID));
    }

    public UUID getUserFromServer(DbNonTmpGameServer server) {
        return super.getFirst(Column.EndGame.PLAYER_UUID, new TableEntry<>(server.getName(), Column.EndGame.SERVER));
    }
}
