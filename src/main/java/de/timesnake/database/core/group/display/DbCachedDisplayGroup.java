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

package de.timesnake.database.core.group.display;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.DbCachedGroup;
import de.timesnake.database.util.object.ColumnMap;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class DbCachedDisplayGroup extends DbCachedGroup implements de.timesnake.database.util.group.DbDisplayGroup {

    protected boolean showAlways;

    public DbCachedDisplayGroup(DbDisplayGroup database) {
        super(database);

        ColumnMap map = this.database.getFirstWithKey(Set.of(Column.Group.NAME, Column.Group.PREFIX, Column.Group.PREFIX_COLOR,
                Column.Group.SHOW_ALWAYS));

        this.name = map.get(Column.Group.NAME);
        this.prefix = map.get(Column.Group.PREFIX);
        this.color = map.get(Column.Group.PREFIX_COLOR);
        this.showAlways = map.get(Column.Group.SHOW_ALWAYS);
    }

    @Override
    public DbDisplayGroup getDatabase() {
        return (DbDisplayGroup) super.getDatabase();
    }

    @Override
    public boolean showAlways() {
        return this.showAlways;
    }

    @Override
    public void setShowAlways(boolean showAlways) {
        this.showAlways = showAlways;
        this.getDatabase().setShowAlways(showAlways);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbDisplayGroup toLocal() {
        return this.getDatabase().toLocal();
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbDisplayGroup toDatabase() {
        return this.getDatabase();
    }
}
