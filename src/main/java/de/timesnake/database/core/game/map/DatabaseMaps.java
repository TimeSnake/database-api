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

package de.timesnake.database.core.game.map;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DatabaseMaps extends DatabaseConnector {

    private final String infoTableName;
    private final String spawnsTableName;
    private final String authorsTableName;

    public DatabaseMaps(String name, String url, String user, String password, String infoTableName,
                        String spawnsTableName, String authorsTableName) {
        super(name, url, user, password);
        this.infoTableName = infoTableName;
        this.spawnsTableName = spawnsTableName;
        this.authorsTableName = authorsTableName;
    }

    public void addGame(String gameName) {
        new MapLocationsTable(this, gameName).create();
    }

    public void deleteGameMaps(String gameName) {
        new MapLocationsTable(this, gameName).delete();
    }

    @NotNull
    public MapsInfoTable getMapsInfoTable(String gameName) {
        return new MapsInfoTable(this, gameName + "_" + this.infoTableName);
    }

    @NotNull
    public MapLocationsTable getMapsSpawnsTable(String gameName) {
        return new MapLocationsTable(this, gameName + "_" + this.spawnsTableName);
    }

    @NotNull
    public MapsAuthorTable getMapsAuthorTable(String gameName) {
        return new MapsAuthorTable(this, gameName + "_" + this.authorsTableName);
    }

}
