/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group.perm;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.GroupBasisTable;
import de.timesnake.database.util.object.DatabaseConnector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class PermGroupsTable extends GroupBasisTable {

  public PermGroupsTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName);
    super.addColumn(Column.Group.INHERITANCE);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void backup() {
    super.backup();
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
