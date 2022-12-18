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

package de.timesnake.database.core.game.lounge;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;
import org.jetbrains.annotations.NotNull;

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
        super.addEntry(new PrimaryEntries(new Entry<>(name, Column.Game.LOUNGE_MAP_NAME)),
                new Entry<>(spawn.getWorldName(), Column.Game.LOUNGE_MAP_WORLD),
                new Entry<>(spawn.getX(), Column.Game.LOUNGE_MAP_LOC_X),
                new Entry<>(spawn.getY(), Column.Game.LOUNGE_MAP_LOC_Y),
                new Entry<>(spawn.getZ(), Column.Game.LOUNGE_MAP_LOC_Z),
                new Entry<>(spawn.getYaw(), Column.Game.LOUNGE_MAP_LOC_YAW),
                new Entry<>(spawn.getPitch(), Column.Game.LOUNGE_MAP_LOC_PITCH));
    }

    public void removeMap(String name) {
        super.deleteEntry(new Entry<>(name, Column.Game.LOUNGE_MAP_NAME));
    }

    public boolean containsMap(String name, DbLoungeMapDisplayTable displayTable) {
        return this.getMap(name, displayTable).exists();
    }

    @NotNull
    public de.timesnake.database.util.game.DbLoungeMap getMap(String name, DbLoungeMapDisplayTable displayTable) {
        return new DbLoungeMap(this.databaseConnector, this.tableName, name, displayTable);
    }

    public Collection<de.timesnake.database.util.game.DbLoungeMap> getMaps(DbLoungeMapDisplayTable displayTable) {
        return super.get(Column.Game.LOUNGE_MAP_NAME).stream()
                .map(name -> this.getMap(name, displayTable)).toList();
    }

    public Collection<de.timesnake.database.util.game.DbLoungeMap> getCachedMaps(DbLoungeMapDisplayTable displayTable) {
        return super.get(Column.Game.LOUNGE_MAP_NAME).stream()
                .map(name -> this.getMap(name, displayTable).toLocal()).toList();
    }
}
