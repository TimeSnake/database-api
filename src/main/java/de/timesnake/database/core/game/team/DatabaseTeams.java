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

package de.timesnake.database.core.game.team;

import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DatabaseTeams extends DatabaseConnector {

    public DatabaseTeams(String name, String url, String options, String user, String password) {
        super(name, url, options, user, password);
    }

    public void addGame(DbGame game) {
        new TeamsTable(this, game.getInfo().getName()).create();
    }

    @NotNull
    public TeamsTable getGameTeams(String gameName) {
        return new TeamsTable(this, gameName);
    }

    public void deleteGameTeams(DbGame game) {
        TeamsTable teamsTable = new TeamsTable(this, game.getInfo().getName());
        teamsTable.delete();
    }
}
