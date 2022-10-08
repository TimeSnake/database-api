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

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbDisplayGroup;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

public class DbDisplayGroupUser extends TableQuery {

    protected DbDisplayGroupUser(DatabaseConnector databaseConnector, String nameTable, UUID uuid) {
        super(databaseConnector, nameTable, new TableEntry<>(uuid, Column.User.UUID));
    }

    @Override
    public boolean exists() {
        return !this.getDisplayGroupNames().isEmpty();
    }

    @NotNull
    public UUID getUuid() {
        return (UUID) this.primaryEntries.get(0).getValue();
    }

    public Collection<String> getDisplayGroupNames() {
        return super.getWithKey(Column.User.DISPLAY_GROUP);
    }

    public Collection<DbDisplayGroup> getDisplayGroups() {
        Collection<DbDisplayGroup> groups = new LinkedList<>();
        for (String name : this.getDisplayGroupNames()) {
            groups.add(Database.getGroups().getDisplayGroup(name));
        }

        return groups;
    }

    public void addDisplayGroup(String name) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(this.getUuid(), Column.User.UUID),
                new TableEntry<>(name, Column.User.DISPLAY_GROUP)));
    }

    public void removeDisplayGroup(String name) {
        super.deleteWithKey(new TableEntry<>(name, Column.User.DISPLAY_GROUP));
    }

    public void clearDisplayGroups() {
        super.deleteWithKey();
    }
}
