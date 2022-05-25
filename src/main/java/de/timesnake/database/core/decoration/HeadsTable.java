package de.timesnake.database.core.decoration;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class HeadsTable extends Table {

    protected HeadsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Decoration.HEAD_TAG);
        super.addColumn(Column.Decoration.HEAD_NAME);
        super.addColumn(Column.Decoration.HEAD_SECTION);
    }

    @Override
    public void create() {
        super.create();
    }

    public void backup() {
        super.createBackup();
    }

    public boolean containsHead(String tag) {
        return super.getFirst(Column.Decoration.HEAD_TAG, new TableEntry<>(tag, Column.Decoration.HEAD_TAG)) != null;
    }

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
