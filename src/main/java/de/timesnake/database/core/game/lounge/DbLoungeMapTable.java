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

package de.timesnake.database.core.game.lounge;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class DbLoungeMapTable extends TableDDL {

    protected DbLoungeMapTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.LOUNGE_MAP_NAME);
        super.addColumn(Column.Game.LOUNGE_MAP_WORLD);
        super.addColumn(Column.Game.LOUNGE_MAP_LOC_X);
        super.addColumn(Column.Game.LOUNGE_MAP_LOC_Y);
        super.addColumn(Column.Game.LOUNGE_MAP_LOC_Z);
        super.addColumn(Column.Game.LOUNGE_MAP_LOC_YAW);
        super.addColumn(Column.Game.LOUNGE_MAP_LOC_PITCH);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public void addMap(String name, DbLocation spawn) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(name, Column.Game.LOUNGE_MAP_NAME)),
                new TableEntry<>(spawn.getWorldName(), Column.Game.LOUNGE_MAP_WORLD),
                new TableEntry<>(spawn.getX(), Column.Game.LOUNGE_MAP_LOC_X),
                new TableEntry<>(spawn.getY(), Column.Game.LOUNGE_MAP_LOC_Y),
                new TableEntry<>(spawn.getZ(), Column.Game.LOUNGE_MAP_LOC_Z),
                new TableEntry<>(spawn.getYaw(), Column.Game.LOUNGE_MAP_LOC_YAW),
                new TableEntry<>(spawn.getPitch(), Column.Game.LOUNGE_MAP_LOC_PITCH));
    }

    public void removeMap(String name) {
        super.deleteEntry(new TableEntry<>(name, Column.Game.LOUNGE_MAP_NAME));
    }

    public boolean containsMap(String name, DbLoungeMapDisplayTable displayTable) {
        return this.getMap(name, displayTable).exists();
    }

    @NotNull
    public de.timesnake.database.util.game.DbLoungeMap getMap(String name, DbLoungeMapDisplayTable displayTable) {
        return new DbLoungeMap(this.databaseConnector, this.tableName, name, displayTable);
    }

    public Collection<de.timesnake.database.util.game.DbLoungeMap> getMaps(DbLoungeMapDisplayTable displayTable) {
        ArrayList<de.timesnake.database.util.game.DbLoungeMap> maps = new ArrayList<>();
        ArrayList<String> mapNames = new ArrayList<>();
        for (String mapName : super.get(Column.Game.LOUNGE_MAP_NAME)) {
            de.timesnake.database.util.game.DbLoungeMap map = this.getMap(mapName, displayTable);
            if (!mapNames.contains(mapName)) {
                maps.add(map);
                mapNames.add(mapName);
            }
        }
        return maps;
    }

    public Collection<de.timesnake.database.util.game.DbLoungeMap> getCachedMaps(DbLoungeMapDisplayTable displayTable) {
        ArrayList<de.timesnake.database.util.game.DbLoungeMap> maps = new ArrayList<>();
        ArrayList<String> mapNames = new ArrayList<>();
        for (String mapName : super.get(Column.Game.LOUNGE_MAP_NAME)) {
            de.timesnake.database.util.game.DbLoungeMap map = this.getMap(mapName, displayTable);
            if (!mapNames.contains(mapName)) {
                maps.add(map);
                mapNames.add(mapName);
            }
        }
        return maps;
    }
}
