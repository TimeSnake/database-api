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

package de.timesnake.database.core.decoration;

import de.timesnake.database.util.decoration.DbHead;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DatabaseDecoration extends DatabaseConnector implements de.timesnake.database.util.decoration.DatabaseDecoration {

    private final String headsTableName;
    private final HeadsTable headsTable;

    public DatabaseDecoration(String name, String url, String user, String password, String headsTableName) {
        super(name, url, user, password);
        this.headsTableName = headsTableName;

        this.headsTable = new HeadsTable(this, this.headsTableName);
    }

    @Override
    public void createTables() {
        this.headsTable.create();
    }

    @Override
    public void backupTables() {
        this.headsTable.backup();
    }

    @Nullable
    @Override
    public DbHead getHead(String tag) {
        return this.headsTable.getHead(tag);
    }

    @Override
    public boolean containsHead(String tag) {
        return this.headsTable.containsHead(tag);
    }

    @Override
    public boolean addHead(String tag, String name, String section) {
        return this.headsTable.addHead(tag, name, section);
    }

    @Override
    public boolean removeHead(String tag) {
        return this.headsTable.removeHead(tag);
    }

    @NotNull
    @Override
    public Collection<String> getHeadTags() {
        return this.headsTable.getHeadTags();
    }

    @NotNull
    @Override
    public Collection<String> getHeadTags(String section) {
        return this.headsTable.getHeadTags(section);
    }

    @NotNull
    @Override
    public Collection<DbHead> getHeads() {
        return this.headsTable.getHeads();
    }

    @NotNull
    @Override
    public Collection<DbHead> getHeads(String section) {
        return this.headsTable.getHeads(section);
    }

    @NotNull
    @Override
    public Collection<String> getSections() {
        return this.headsTable.getSections();
    }

}
