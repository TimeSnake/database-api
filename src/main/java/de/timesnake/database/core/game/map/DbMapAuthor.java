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
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class DbMapAuthor extends TableQuery {

    protected DbMapAuthor(DatabaseConnector databaseConnector, String nameTable, String mapName, UUID authorUuid) {
        super(databaseConnector, nameTable, new Entry<>(mapName, Column.Game.MAP_NAME),
                new Entry<>(authorUuid, Column.Game.MAP_AUTHOR_UUID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.Game.MAP_NAME) != null;
    }

    @NotNull
    public String getMapName() {
        return ((String) super.primaryEntries.get(0).getValue());
    }

    @NotNull
    public UUID getAuthorUuid() {
        return ((UUID) super.primaryEntries.get(1).getValue());
    }

    @NotNull
    public String getAuthorName() {
        return Database.getUsers().getUser(this.getAuthorUuid()).getName();
    }
}
