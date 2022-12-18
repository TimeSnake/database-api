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
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class DbPlayer extends TableQuery implements de.timesnake.database.util.user.DbPlayer {

    protected DbPlayer(DatabaseConnector databaseConnector, UUID uuid, String nameTable) {
        super(databaseConnector, nameTable, new Entry<>(uuid, Column.User.UUID));
    }

    @Override
    public boolean exists() {
        return super.getFirst(Column.User.UUID) != null;
    }

    @NotNull
    @Override
    public String getName() {
        return super.getFirstWithKey(Column.User.NAME);
    }

    @NotNull
    @Override
    public UUID getUniqueId() {
        return (UUID) super.primaryEntries.get(0).getValue();
    }

}
