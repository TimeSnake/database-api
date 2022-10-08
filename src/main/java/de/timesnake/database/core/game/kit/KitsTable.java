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

package de.timesnake.database.core.game.kit;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.UnsupportedStringException;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class KitsTable extends TableDDL {

    protected KitsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.KIT_ID);
        super.addColumn(Column.Game.KIT_NAME);
        super.addColumn(Column.Game.KIT_ITEM);
        super.addColumn(Column.Game.KIT_DESCRIPTION);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void backup() {
        super.backup();
    }

    @Override
    public void delete() {
        super.delete();
    }

    public Collection<Integer> getKitsId() {
        return super.get(Column.Game.KIT_ID);
    }

    public de.timesnake.database.util.game.DbKit getKit(int id) {
        return new DbKit(this.databaseConnector, id, tableName);
    }

    @Nullable
    public de.timesnake.database.util.game.DbKit getKit(String name) {
        Integer id = super.getFirst(Column.Game.KIT_ID, new TableEntry<>(name, Column.Game.KIT_NAME));
        if (id != null) {
            return this.getKit(id);
        }
        return null;
    }

    public void removeKit(Integer id) {
        super.deleteEntry(new TableEntry<>(id, Column.Game.KIT_ID));
    }


    public void removeKitSynchronized(Integer id) {
        super.deleteEntrySynchronized(new TableEntry<>(id, Column.Game.KIT_ID));
    }

    public void addKit(Integer id, String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        for (String string : description) {
            if (string.contains(Table.ENTRY_ARRAY_SPLITTER)) {
                throw new UnsupportedStringException(Table.ENTRY_ARRAY_SPLITTER);
            }
        }
        super.addEntry(new PrimaryEntries(new TableEntry<>(id, Column.Game.KIT_ID)), new TableEntry<>(name,
                        Column.Game.KIT_NAME), new TableEntry<>(itemType, Column.Game.KIT_ITEM),
                new TableEntry<>(new DbStringArrayList(description), Column.Game.KIT_DESCRIPTION));
    }

    public void addKit(String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        for (String string : description) {
            if (string.contains(Table.ENTRY_ARRAY_SPLITTER)) {
                throw new UnsupportedStringException(Table.ENTRY_ARRAY_SPLITTER);
            }
        }
        super.addEntryWithAutoIdSynchronized(Column.Game.KIT_ID, new TableEntry<>(name, Column.Game.KIT_NAME),
                new TableEntry<>(itemType, Column.Game.KIT_ITEM), new TableEntry<>(new DbStringArrayList(description)
                        , Column.Game.KIT_DESCRIPTION));
    }
}
