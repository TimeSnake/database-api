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

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class MapsAuthorTable extends TableDDL {

    protected MapsAuthorTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.MAP_NAME, Column.Game.MAP_AUTHOR_UUID);
    }

    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public void delete() {
        super.delete();
    }

    public void addMapAuthor(String mapName, UUID authorUuid) {
        super.addEntry(new PrimaryEntries(new Entry<>(mapName, Column.Game.MAP_NAME),
                new Entry<>(authorUuid, Column.Game.MAP_AUTHOR_UUID)));
    }

    public void removeMapAuthor(String mapName, UUID authorUuid) {
        super.deleteEntry(new Entry<>(mapName, Column.Game.MAP_NAME), new Entry<>(authorUuid,
                Column.Game.MAP_AUTHOR_UUID));
    }

    @NotNull
    public DbMapAuthor getMapAuthor(String mapName, UUID authorUuid) {
        return new DbMapAuthor(this.databaseConnector, this.tableName, mapName, authorUuid);
    }

    public Collection<UUID> getAuthorUuids(String mapName) {
        return super.get(Column.Game.MAP_AUTHOR_UUID, new Entry<>(mapName, Column.Game.MAP_NAME));
    }

    public Collection<DbMapAuthor> getAuthors(String mapName) {
        ArrayList<DbMapAuthor> authors = new ArrayList<>();
        for (UUID author : super.get(Column.Game.MAP_AUTHOR_UUID, new Entry<>(mapName, Column.Game.MAP_NAME))) {
            authors.add(new DbMapAuthor(this.databaseConnector, this.tableName, mapName, author));
        }
        return authors;
    }

    public void removeMapAuthors(String mapName) {
        super.deleteEntry(new Entry<>(mapName, Column.Game.MAP_NAME));
    }
}
