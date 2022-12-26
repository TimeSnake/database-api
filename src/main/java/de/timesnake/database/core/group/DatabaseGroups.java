/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.core.group;

import de.timesnake.database.core.group.display.DbDisplayGroup;
import de.timesnake.database.core.group.display.DisplayGroupsTable;
import de.timesnake.database.core.group.perm.DbPermGroup;
import de.timesnake.database.core.group.perm.PermGroupsTable;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DatabaseGroups extends DatabaseConnector implements de.timesnake.database.util.group.DatabaseGroups {

    private final PermGroupsTable permGroupsTable;
    private final DisplayGroupsTable displayGroupsTable;

    private final String permGroupsTableName;
    private final String displayGroupsTableName;

    public DatabaseGroups(String name, String url, String options, String user, String password, String permGroupsTableName,
                          String displayGroupsTableName) {
        super(name, url, options, user, password);
        this.permGroupsTableName = permGroupsTableName;
        this.displayGroupsTableName = displayGroupsTableName;
        this.permGroupsTable = new PermGroupsTable(this, this.permGroupsTableName);
        this.displayGroupsTable = new DisplayGroupsTable(this, this.displayGroupsTableName);
    }

    @Override
    public void createTables() {
        this.permGroupsTable.create();
        this.displayGroupsTable.create();
    }

    @Override
    public void backupTables() {
        this.permGroupsTable.backup();
        this.displayGroupsTable.backup();
    }

    @Override
    public void addPermGroup(String name, int rank) {
        this.permGroupsTable.addGroup(name, rank);
    }

    @Override
    public boolean containsPermGroup(String name) {
        return this.permGroupsTable.containsGroup(name);
    }

    @NotNull
    @Override
    public de.timesnake.database.util.group.DbPermGroup getPermGroup(String name) {
        return this.permGroupsTable.getGroup(name);
    }

    @Override
    public void removePermGroup(String name) {
        this.permGroupsTable.removeGroup(name);
    }

    @Override
    public Collection<String> getPermGroupNames() {
        return this.permGroupsTable.getGroupNames();
    }

    @Override
    public Collection<Integer> getPermGroupRanks() {
        return this.permGroupsTable.getGroupRanks();
    }

    @Override
    public Collection<DbPermGroup> getPermGroups() {
        return this.permGroupsTable.getGroups();
    }

    @Override
    public void addDisplayGroup(String name, int rank, String prefix, ExTextColor color) {
        this.displayGroupsTable.addGroup(name, rank, prefix, color);
    }

    @Override
    public boolean containsDisplayGroup(String name) {
        return this.displayGroupsTable.containsGroup(name);
    }

    @Nullable
    @NotNull
    @Override
    public DbDisplayGroup getDisplayGroup(String name) {
        return this.displayGroupsTable.getGroup(name);
    }

    @Override
    public void removeDisplayGroup(String name) {
        this.displayGroupsTable.removeGroup(name);
    }

    @Override
    public Collection<String> getDisplayGroupNames() {
        return this.displayGroupsTable.getGroupNames();
    }

    @Override
    public Collection<Integer> getDisplayGroupRanks() {
        return this.displayGroupsTable.getGroupRanks();
    }

    @Override
    public Collection<DbDisplayGroup> getDisplayGroups() {
        return this.displayGroupsTable.getGroups();
    }

}
