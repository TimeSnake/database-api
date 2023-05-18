/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group.display;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.GroupsTable;
import de.timesnake.database.util.object.DatabaseConnector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class DisplayGroupsTable extends GroupsTable {

  public DisplayGroupsTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable);
    super.addColumn(Column.Group.SHOW_ALWAYS);
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
  public DbDisplayGroup getGroup(String name) {
    return new DbDisplayGroup(this.databaseConnector, name, this.tableName);
  }

  public Collection<DbDisplayGroup> getGroups() {
    List<DbDisplayGroup> groups = new ArrayList<>();
    for (String name : this.getGroupNames()) {
      groups.add(this.getGroup(name));
    }
    return groups;
  }
}
