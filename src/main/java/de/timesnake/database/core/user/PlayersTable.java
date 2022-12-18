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

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public abstract class PlayersTable extends TableDDL {

    public PlayersTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable, Column.User.UUID);
        super.addColumn(Column.User.NAME);
    }


    public Collection<UUID> getPlayerUniqueIds() {
        return super.get(Column.User.UUID);
    }

    public ArrayList<String> getPlayerNames() {
        ArrayList<String> names = new ArrayList<>();
        for (String dbName : super.get(Column.User.NAME)) {
            names.add(dbName.toLowerCase());
        }
        return names;
    }

    public void addPlayer(UUID uuid, String name) {
        super.addEntrySynchronized(new PrimaryEntries(new Entry<>(uuid, Column.User.UUID)),
                new Entry<>(name, Column.User.NAME));
    }

    public void create() {
        super.create();
    }

    public boolean containsPlayer(UUID uuid) {
        return super.getFirst(Column.User.NAME, new Entry<>(uuid, Column.User.UUID)) != null;
    }

    public boolean containsPlayer(String name) {
        return super.getFirst(Column.User.UUID, new Entry<>(name, Column.User.NAME)) != null;
    }

    @Nullable
    public UUID getUniqueIdFromName(String name) {
        return super.getFirst(Column.User.UUID, new Entry<>(name, Column.User.NAME));
    }

}
