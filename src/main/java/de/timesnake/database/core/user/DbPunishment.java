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
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

public class DbPunishment extends DbPlayer implements de.timesnake.database.util.user.DbPunishment {

    public DbPunishment(DatabaseConnector databaseConnector, UUID uuid, String nameTable) {
        super(databaseConnector, uuid, nameTable);
    }

    @Override
    public void delete() {
        super.setWithKey(null, Column.User.PUNISH_TYPE);
        super.setWithKey(null, Column.User.PUNISH_DATE);
        super.setWithKey(null, Column.User.PUNISH_CASTIGATOR);
        super.setWithKey(null, Column.User.PUNISH_REASON);
        super.setWithKey(null, Column.User.PUNISH_SERVER);
    }

    @Nullable
    @Override
    public Type.Punishment getType() {
        return super.getFirstWithKey(Column.User.PUNISH_TYPE);
    }

    @Override
    public void setType(Type.Punishment type) {
        super.setWithKey(type, Column.User.PUNISH_TYPE);
    }

    @Nullable
    @Override
    public LocalDateTime getDate() {
        return super.getFirstWithKey(Column.User.PUNISH_DATE);
    }

    @Override
    public void setDate(LocalDateTime date) {
        super.setWithKey(date, Column.User.PUNISH_DATE);
    }

    @Nullable
    @Override
    public String getCastigator() {
        return super.getFirstWithKey(Column.User.PUNISH_CASTIGATOR);
    }

    @Override
    public void setCastigator(String castigator) {
        super.setWithKey(castigator, Column.User.PUNISH_CASTIGATOR);
    }

    @Nullable
    @Override
    public String getReason() {
        return super.getFirstWithKey(Column.User.PUNISH_REASON);
    }

    @Override
    public void setReason(String reason) {
        super.setWithKey(reason, Column.User.PUNISH_REASON);
    }

    @Nullable
    @Override
    public String getServer() {
        return super.getFirstWithKey(Column.User.PUNISH_SERVER);
    }

    @Override
    public void setServer(String server) {
        super.setWithKey(server, Column.User.PUNISH_SERVER);
    }

}
