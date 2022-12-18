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
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class DbUserMail extends TableQuery implements de.timesnake.database.util.user.DbUserMail {

    public DbUserMail(DatabaseConnector databaseConnector, String nameTable, UUID uuid, Integer id) {
        super(databaseConnector, nameTable, new Entry<>(uuid, Column.User.UUID), new Entry<>(id,
                Column.User.MAIL_ID));
    }

    @Override
    public boolean exists() {
        return super.getFirstWithKey(Column.User.MAIL_ID) != null;
    }

    @NotNull
    @Override
    public Integer getId() {
        return (Integer) super.primaryEntries.get(1).getValue();
    }

    @Nullable
    @Override
    public UUID getUniqueId() {
        return (UUID) super.primaryEntries.get(0).getValue();
    }

    @Nullable
    @Override
    public String getName() {
        return super.getFirst(Column.User.NAME);
    }

    @Nullable
    @Override
    public UUID getSenderUniqueId() {
        return super.getFirst(Column.User.MAIL_SENDER_UUID);
    }

    @Nullable
    @Override
    public String getSenderName() {
        return super.getFirst(Column.User.MAIL_SENDER_NAME);
    }

    @Nullable
    @Override
    public String getMessage() {
        return super.getFirst(Column.User.MAIL_MESSAGE);
    }

    @Override
    public void delete() {
        super.deleteEntry();
    }
}
