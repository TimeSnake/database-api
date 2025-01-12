/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group;

import de.timesnake.database.core.group.display.DbDisplayGroup;
import de.timesnake.database.core.group.display.DisplayGroupTable;
import de.timesnake.database.core.group.perm.DbPermGroup;
import de.timesnake.database.core.group.perm.PermGroupTable;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class DatabaseGroups extends DatabaseConnector implements
    de.timesnake.database.util.group.DatabaseGroups {

  private final PermGroupTable permGroupTable;
  private final DisplayGroupTable displayGroupTable;

  public DatabaseGroups(String name, String url, String options, String user, String password,
      String permGroupsTableName,
      String displayGroupsTableName) {
    super(name, url, options, user, password);
    this.permGroupTable = new PermGroupTable(this, permGroupsTableName);
    this.displayGroupTable = new DisplayGroupTable(this, displayGroupsTableName);
  }

  @Override
  public void createTables() {
    this.permGroupTable.create();
    this.displayGroupTable.create();
  }

  @Override
  public void saveTables() {
    this.permGroupTable.save();
    this.displayGroupTable.save();
  }

  @Override
  public void addPermGroup(String name, int rank) {
    this.permGroupTable.addGroup(name, rank);
  }

  @Override
  public boolean containsPermGroup(String name) {
    return this.permGroupTable.containsGroup(name);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.group.DbPermGroup getPermGroup(String name) {
    return this.permGroupTable.getGroup(name);
  }

  @Override
  public void removePermGroup(String name) {
    this.permGroupTable.removeGroup(name);
  }

  @Override
  public Collection<String> getPermGroupNames() {
    return this.permGroupTable.getGroupNames();
  }

  @Override
  public Collection<Integer> getPermGroupRanks() {
    return this.permGroupTable.getGroupRanks();
  }

  @Override
  public Collection<DbPermGroup> getPermGroups() {
    return this.permGroupTable.getGroups();
  }

  @Override
  public void addDisplayGroup(String name, int rank, String prefix, ExTextColor color) {
    this.displayGroupTable.addGroup(name, rank, prefix, color);
  }

  @Override
  public boolean containsDisplayGroup(String name) {
    return this.displayGroupTable.containsGroup(name);
  }

  @NotNull
  @Override
  public DbDisplayGroup getDisplayGroup(String name) {
    return this.displayGroupTable.getGroup(name);
  }

  @Override
  public void removeDisplayGroup(String name) {
    this.displayGroupTable.removeGroup(name);
  }

  @Override
  public Collection<String> getDisplayGroupNames() {
    return this.displayGroupTable.getGroupNames();
  }

  @Override
  public Collection<Integer> getDisplayGroupRanks() {
    return this.displayGroupTable.getGroupRanks();
  }

  @Override
  public Collection<DbDisplayGroup> getDisplayGroups() {
    return this.displayGroupTable.getGroups();
  }

}
