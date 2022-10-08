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

import org.jetbrains.annotations.NotNull;

public class DbCachedGroupBasis implements de.timesnake.database.util.group.DbGroupBasis {

    protected final DbGroupBasis database;

    protected final int rank;
    protected String name;

    public DbCachedGroupBasis(DbGroupBasis database) {
        this.database = database;
        this.rank = this.database.getRank();
    }

    public de.timesnake.database.util.group.DbGroupBasis getDatabase() {
        return database;
    }

    @Override
    public boolean exists() {
        return this.database.exists();
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        this.database.setName(name);
    }

    @NotNull
    @Override
    public Integer getRank() {
        return this.rank;
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroupBasis toLocal() {
        return this.database.toLocal();
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbGroupBasis toDatabase() {
        return this.database;
    }
}
