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

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class HeadsTable extends TableDDL {

    protected HeadsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Decoration.HEAD_TAG);
        super.addColumn(Column.Decoration.HEAD_NAME);
        super.addColumn(Column.Decoration.HEAD_SECTION);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    public boolean containsHead(String tag) {
        return super.getFirst(Column.Decoration.HEAD_TAG, new TableEntry<>(tag, Column.Decoration.HEAD_TAG)) != null;
    }

    @Nullable
    public de.timesnake.database.util.decoration.DbHead getHead(String tag) {
        return this.containsHead(tag) ? new DbHead(this.databaseConnector, this.tableName, tag) : null;
    }

    public boolean addHead(String tag, String name, String section) {
        if (tag == null) {
            return false;
        }
        super.addEntry(new PrimaryEntries(new TableEntry<>(tag, Column.Decoration.HEAD_TAG)), new TableEntry<>(name,
                Column.Decoration.HEAD_NAME), new TableEntry<>(section, Column.Decoration.HEAD_SECTION));
        return true;

    }

    public boolean removeHead(String tag) {
        if (tag == null) {
            return false;
        }
        super.deleteEntry(new TableEntry<>(tag, Column.Decoration.HEAD_TAG));
        return true;
    }

    public Collection<String> getHeadTags() {
        return super.get(Column.Decoration.HEAD_TAG);
    }

    public Collection<String> getHeadTags(String section) {
        return super.get(Column.Decoration.HEAD_TAG, new TableEntry<>(section, Column.Decoration.HEAD_SECTION));
    }

    public Collection<de.timesnake.database.util.decoration.DbHead> getHeads() {
        Collection<de.timesnake.database.util.decoration.DbHead> heads = new ArrayList<>();
        for (String tag : this.getHeadTags()) {
            de.timesnake.database.util.decoration.DbHead head = this.getHead(tag);
            if (head != null) {
                heads.add(head);
            }
        }
        return heads;
    }

    public Collection<de.timesnake.database.util.decoration.DbHead> getHeads(String section) {
        Collection<de.timesnake.database.util.decoration.DbHead> heads = new ArrayList<>();
        for (String tag : this.getHeadTags(section)) {
            de.timesnake.database.util.decoration.DbHead head = this.getHead(tag);
            if (head != null) {
                heads.add(head);
            }
        }
        return heads;
    }

    public Collection<String> getSections() {
        return new HashSet<>(super.get(Column.Decoration.HEAD_SECTION));
    }
}
