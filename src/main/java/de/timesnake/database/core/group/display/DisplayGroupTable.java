/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group.display;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.GroupTable;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DisplayGroupTable extends GroupTable {

  public DisplayGroupTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable);
    super.addColumn(Column.Group.SHOW_ALWAYS);
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
