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

package de.timesnake.database.core.game.kit;

import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DatabaseKits extends DatabaseConnector {

    public DatabaseKits(String name, String url, String user, String password) {
        super(name, url, user, password);
    }

    @NotNull
    public KitsTable getGameKits(String gameName) {
        return new KitsTable(this, gameName);
    }

    public void deleteGameKits(DbGame game) {
        KitsTable teamsTable = new KitsTable(this, game.getInfo().getName());
        teamsTable.delete();
    }
}
