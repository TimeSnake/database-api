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

package de.timesnake.database.core.game.lounge;

import de.timesnake.database.util.game.DbLoungeMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class DatabaseLounges extends DatabaseConnector implements de.timesnake.database.util.game.DatabaseLounges {

    protected DbLoungeMapTable loungeMapTable;
    protected DbLoungeMapDisplayTable loungeMapDisplayTable;

    public DatabaseLounges(String name, String url, String options, String user, String password, String loungeMapTableName,
                           String loungeMapDisplayTableName) {
        super(name, url, options, user, password);

        this.loungeMapTable = new DbLoungeMapTable(this, loungeMapTableName);
        this.loungeMapDisplayTable = new DbLoungeMapDisplayTable(this, loungeMapDisplayTableName);
    }

    @Override
    public void createTables() {
        this.loungeMapTable.create();
        this.loungeMapDisplayTable.create();
    }

    @Override
    public void backupTables() {
        this.loungeMapTable.backup();
        this.loungeMapDisplayTable.backup();
    }

    @Override
    public void addMap(String name, DbLocation spawn) {
        this.loungeMapTable.addMap(name, spawn);
    }

    @Override
    public void removeMap(String name) {
        this.loungeMapTable.removeMap(name);
    }


    @Override
    public boolean containsMap(String name) {
        return this.loungeMapTable.containsMap(name, this.loungeMapDisplayTable);
    }


    @NotNull
    @Override
    public de.timesnake.database.util.game.DbLoungeMap getMap(String name) {
        return this.loungeMapTable.getMap(name, this.loungeMapDisplayTable);
    }

    @NotNull
    @Override
    public Collection<DbLoungeMap> getMaps() {
        return this.loungeMapTable.getMaps(this.loungeMapDisplayTable);
    }

    @NotNull
    @Override
    public Collection<DbLoungeMap> getCachedMaps() {
        return this.loungeMapTable.getCachedMaps(this.loungeMapDisplayTable);
    }
}
