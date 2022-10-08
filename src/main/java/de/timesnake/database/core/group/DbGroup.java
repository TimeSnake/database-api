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

package de.timesnake.database.core.group;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbGroup extends DbGroupBasis implements de.timesnake.database.util.group.DbGroup {


    protected DbGroup(DatabaseConnector databaseConnector, String name, String tableName) {
        super(databaseConnector, name, tableName);
    }

    @Nullable
    @Override
    public String getPrefix() {
        return super.getFirstWithKey(Column.Group.PREFIX);
    }

    @Override
    public void setPrefix(String prefix) {
        super.setWithKey(prefix, Column.Group.PREFIX);
    }

    @Override
    @Deprecated
    @Nullable
    public String getChatColorName() {
        ExTextColor color = this.getChatColor();
        return color != null ? color.toString() : null;
    }

    @Override
    @Deprecated
    public void setChatColorName(String chatColorName) {
        super.setWithKey(ExTextColor.NAMES.value(chatColorName), Column.Group.PREFIX_COLOR);
    }

    @Nullable
    @Override
    public ExTextColor getChatColor() {
        return super.getFirstWithKey(Column.Group.PREFIX_COLOR);
    }

    @Override
    public void setChatColor(ExTextColor color) {
        super.setWithKey(color, Column.Group.PREFIX_COLOR);
    }


    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroup toLocal() {
        return new DbCachedGroup(this);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroup toDatabase() {
        return this;
    }

}
