/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.kit;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.database.util.object.UnsupportedStringException;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public List<Integer> getKitsId() {
        return new ArrayList<>(super.get(Column.Game.KIT_ID));
    }

    public de.timesnake.database.util.game.DbKit getKit(int id) {
        return new DbKit(this.databaseConnector, id, tableName);
    }

    @Nullable
    public de.timesnake.database.util.game.DbKit getKit(String name) {
        Integer id = super.getFirst(Column.Game.KIT_ID, new Entry<>(name, Column.Game.KIT_NAME));
        if (id != null) {
            return this.getKit(id);
        }
        return null;
    }

    public void removeKit(Integer id) {
        super.deleteEntry(new Entry<>(id, Column.Game.KIT_ID));
    }


    public void removeKitSynchronized(Integer id) {
        super.deleteEntrySynchronized(new Entry<>(id, Column.Game.KIT_ID));
    }

    public void addKit(Integer id, String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        for (String string : description) {
            if (string.contains(Table.ENTRY_ARRAY_DELIMITER)) {
                throw new UnsupportedStringException(Table.ENTRY_ARRAY_DELIMITER);
            }
        }
        super.addEntry(new PrimaryEntries(new Entry<>(id, Column.Game.KIT_ID)), new Entry<>(name,
                        Column.Game.KIT_NAME), new Entry<>(itemType, Column.Game.KIT_ITEM),
                new Entry<>(new DbStringArrayList(description), Column.Game.KIT_DESCRIPTION));
    }

    public void addKit(String name, String itemType, Collection<String> description) throws UnsupportedStringException {
        for (String string : description) {
            if (string.contains(Table.ENTRY_ARRAY_DELIMITER)) {
                throw new UnsupportedStringException(Table.ENTRY_ARRAY_DELIMITER);
            }
        }
        super.addEntryWithAutoIdSynchronized(Column.Game.KIT_ID, new Entry<>(name, Column.Game.KIT_NAME),
                new Entry<>(itemType, Column.Game.KIT_ITEM), new Entry<>(new DbStringArrayList(description)
                        , Column.Game.KIT_DESCRIPTION));
    }
}
