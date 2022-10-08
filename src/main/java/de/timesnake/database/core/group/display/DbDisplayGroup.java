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
import de.timesnake.database.core.group.DbGroup;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbDisplayGroup extends DbGroup implements de.timesnake.database.util.group.DbDisplayGroup {

    public DbDisplayGroup(DatabaseConnector databaseConnector, String name, String nameTable) {
        super(databaseConnector, name, nameTable);
    }

    @Override
    public boolean showAlways() {
        return super.getFirstWithKey(Column.Group.SHOW_ALWAYS);
    }

    @Override
    public void setShowAlways(boolean showAlways) {
        super.setWithKey(showAlways, Column.Group.SHOW_ALWAYS);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbDisplayGroup toLocal() {
        return new DbCachedDisplayGroup(this);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbDisplayGroup toDatabase() {
        return this;
    }

}
