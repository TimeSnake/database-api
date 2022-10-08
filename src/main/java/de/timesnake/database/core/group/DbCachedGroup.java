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

import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbCachedGroup extends DbCachedGroupBasis implements de.timesnake.database.util.group.DbGroup {

    protected String prefix;
    protected ExTextColor color;

    public DbCachedGroup(DbGroup database) {
        super(database);
    }

    @Override
    public DbGroup getDatabase() {
        return (DbGroup) super.getDatabase();
    }

    @Nullable
    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.getDatabase().setPrefix(prefix);
    }

    @Nullable
    @Override
    public String getChatColorName() {
        return this.color.toString();
    }

    @Override
    @Deprecated
    public void setChatColorName(String chatColorName) {
        this.color = ExTextColor.NAMES.value(chatColorName);
        this.getDatabase().setChatColor(color);
    }

    @Nullable
    @Override
    public ExTextColor getChatColor() {
        return this.color;
    }

    @Override
    public void setChatColor(ExTextColor color) {
        this.color = color;
        this.getDatabase().setChatColor(color);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroup toLocal() {
        return this.getDatabase().toLocal();
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroup toDatabase() {
        return this.getDatabase();
    }
}
