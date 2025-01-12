/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbDisplayGroup;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

public class DbDisplayGroupUser extends KeyedQueryTool {

  protected DbDisplayGroupUser(DatabaseConnector databaseConnector, String nameTable, UUID uuid) {
    super(databaseConnector, nameTable, true, new Entry<>(uuid, Column.User.UUID));
  }

  @Override
  public boolean exists() {
    return !this.getDisplayGroupNames().isEmpty();
  }

  @NotNull
  public UUID getUuid() {
    return this.keyEntries.get(Column.User.UUID).getValue();
  }

  public Collection<String> getDisplayGroupNames() {
    return super.getWithKey(Column.User.DISPLAY_GROUP);
  }

  public Collection<DbDisplayGroup> getDisplayGroups() {
    Collection<DbDisplayGroup> groups = new LinkedList<>();
    for (String name : this.getDisplayGroupNames()) {
      groups.add(Database.getGroups().getDisplayGroup(name));
    }

    return groups;
  }

  public void addDisplayGroup(String name) {
    super.addEntry(new PrimaryKeyEntries(new Entry<>(this.getUuid(), Column.User.UUID),
        new Entry<>(name, Column.User.DISPLAY_GROUP)));
  }

  public void removeDisplayGroup(String name) {
    super.deleteWithKey(new Entry<>(name, Column.User.DISPLAY_GROUP));
  }

  public void clearDisplayGroups() {
    super.deleteWithKey();
  }
}
