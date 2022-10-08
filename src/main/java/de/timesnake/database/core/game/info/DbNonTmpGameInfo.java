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

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbNonTmpGameInfo extends DbGameInfo implements de.timesnake.database.util.game.DbNonTmpGameInfo {

    public DbNonTmpGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
        super(databaseConnector, nameTable, gameName);
    }

    @Override
    public boolean isCreationRequestable() {
        return super.getFirstWithKey(Column.Game.CREATION_REQUESTABLE);
    }

    @Override
    public void setCreationRequestable(Boolean creationRequestable) {
        super.setWithKey(creationRequestable, Column.Game.CREATION_REQUESTABLE);
    }

    @Override
    public boolean isOwnable() {
        return super.getFirstWithKey(Column.Game.OWNABLE);
    }

    @Override
    public void setOwnable(Boolean ownable) {
        super.setWithKey(ownable, Column.Game.OWNABLE);
    }

    @Override
    public boolean isNetherAndEndAllowed() {
        return super.getFirstWithKey(Column.Game.ALLOW_NETHER_END);
    }

    @Override
    public void allowNetherAndEnd(Boolean allow) {
        super.setWithKey(allow, Column.Game.ALLOW_NETHER_END);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbNonTmpGameInfo toDatabase() {
        return this;
    }

    @NotNull
    @Override
    public de.timesnake.database.util.game.DbNonTmpGameInfo toLocal() {
        return new DbCachedNonTmpGameInfo(this);
    }
}
