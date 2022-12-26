/*
 * Copyright (C) 2022 timesnake
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

    public DatabaseDecoration(String name, String url, String options, String user, String password, String headsTableName) {
        super(name, url, options, user, password);
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
