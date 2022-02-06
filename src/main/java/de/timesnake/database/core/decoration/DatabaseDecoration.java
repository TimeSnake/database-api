package de.timesnake.database.core.decoration;

import de.timesnake.database.util.decoration.DbHead;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;

public class DatabaseDecoration extends DatabaseConnector implements de.timesnake.database.util.decoration.DatabaseDecoration {

    private final String headsTableName;
    private final HeadsTable headsTable;

    public DatabaseDecoration(String name, String url, String user, String password, String headsTableName) {
        super(name, url, user, password);
        this.headsTableName = headsTableName;

        this.headsTable = new HeadsTable(this, this.headsTableName);
    }

    public void createTables() {
        this.headsTable.create();
    }

    public void backupTables() {
        this.headsTable.backup();
    }

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

    @Override
    public Collection<String> getHeadTags() {
        return this.headsTable.getHeadTags();
    }

    @Override
    public Collection<String> getHeadTags(String section) {
        return this.headsTable.getHeadTags(section);
    }

    @Override
    public Collection<DbHead> getHeads() {
        return this.headsTable.getHeads();
    }

    @Override
    public Collection<DbHead> getHeads(String section) {
        return this.headsTable.getHeads(section);
    }

    @Override
    public Collection<String> getSections() {
        return this.headsTable.getSections();
    }

}
