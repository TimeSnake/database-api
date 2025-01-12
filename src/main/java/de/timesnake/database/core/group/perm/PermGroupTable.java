/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group.perm;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.SimpleGroupTable;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PermGroupTable extends SimpleGroupTable {

  public PermGroupTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName);
    super.addColumn(Column.Group.INHERITANCE);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  @NotNull
  public DbPermGroup getGroup(String name) {
    return new DbPermGroup(this.databaseConnector, name, this.tableName);
  }

  public Collection<DbPermGroup> getGroups() {
    List<DbPermGroup> groups = new ArrayList<>();
    for (String name : this.getGroupNames()) {
      groups.add(this.getGroup(name));
    }
    return groups;
  }
}
