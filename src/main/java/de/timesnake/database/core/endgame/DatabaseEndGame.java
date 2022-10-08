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

import de.timesnake.database.util.endgame.DbEndGameUser;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbNonTmpGameServer;

import java.util.UUID;

public class DatabaseEndGame extends DatabaseConnector implements de.timesnake.database.util.endgame.DatabaseEndGame {

    private final String usersTableName;
    private final EndGamesTable usersTable;


    public DatabaseEndGame(String name, String url, String user, String password, String usersTableName) {
        super(name, url, user, password);
        this.usersTableName = usersTableName;

        this.usersTable = new EndGamesTable(this, this.usersTableName);
    }

    @Override
    public void createTables() {
        this.usersTable.create();
    }

    @Override
    public void backupTables() {
        this.usersTable.backup();
    }

    @Override
    public DbEndGameUser getUser(UUID uuid) {
        return this.usersTable.getUser(uuid);
    }

    @Override
    public boolean containsUser(UUID uuid) {
        return this.usersTable.containsUser(uuid);
    }

    @Override
    public void addUser(UUID uuid, String name, DbNonTmpGameServer server) {
        this.usersTable.addUser(uuid, name, server);
    }

    @Override
    public void removeUser(UUID uuid) {
        this.usersTable.removeUser(uuid);
    }

    @Override
    public UUID getUserFromServer(DbNonTmpGameServer server) {
        return this.usersTable.getUserFromServer(server);
    }


}
