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

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.main.DatabaseManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MapsTable {

    private final String gameName;

    private final MapsInfoTable infoTable;
    private final MapLocationsTable spawnsTable;
    private final MapsAuthorTable authorTable;

    public MapsTable(String gameName) {
        this.infoTable = DatabaseManager.getInstance().getGameMaps().getMapsInfoTable(gameName);
        this.spawnsTable = DatabaseManager.getInstance().getGameMaps().getMapsSpawnsTable(gameName);
        this.authorTable = DatabaseManager.getInstance().getGameMaps().getMapsAuthorTable(gameName);
        this.gameName = gameName;
    }

    public void create() {
        this.infoTable.create();
        this.spawnsTable.create();
        this.authorTable.create();
    }

    public void backup() {
        this.infoTable.backup();
        this.spawnsTable.backup();
        this.authorTable.backup();
    }

    public void delete() {
        this.infoTable.delete();
        this.spawnsTable.delete();
        this.authorTable.delete();
    }

    public void addMap(String name, String displayName, Integer minPlayers, Integer maxPlayers, String itemName,
                       List<String> description, List<String> info, List<String> authors) {
        this.infoTable.addMapInfo(name, displayName, minPlayers, maxPlayers, itemName, description, info);
        this.getMap(name).setAuthorNames(authors);
    }

    public void removeMap(String name) {
        this.infoTable.removeMapInfo(name);
        this.spawnsTable.deleteMap(name);
    }

    @NotNull
    public de.timesnake.database.util.game.DbMap getMap(String mapName) {
        return new DbMap(gameName, mapName);
    }

    public List<de.timesnake.database.util.game.DbMap> getMaps() {
        List<de.timesnake.database.util.game.DbMap> maps = new ArrayList<>();
        for (DbMapInfo info : this.infoTable.getMaps()) {
            maps.add(this.getMap(info.getName()));
        }
        return maps;
    }

    public List<de.timesnake.database.util.game.DbMap> getMaps(Integer players) {
        List<de.timesnake.database.util.game.DbMap> maps = new ArrayList<>();
        for (DbMapInfo info : this.infoTable.getMaps(players)) {
            maps.add(this.getMap(info.getName()));
        }
        return maps;
    }

    public boolean containsMap(String mapName) {
        return this.infoTable.containsMapInfo(mapName);
    }
}
