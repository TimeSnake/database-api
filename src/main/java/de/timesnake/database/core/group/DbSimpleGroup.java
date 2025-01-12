/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbSimpleGroup extends KeyedQueryTool implements
    de.timesnake.database.util.group.DbGroupBasis {

  protected DbSimpleGroup(DatabaseConnector databaseConnector, String name, String nameTable) {
    super(databaseConnector, nameTable, true, new Entry<>(name, Column.Group.NAME));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Group.PRIORITY) != null;
  }

  @NotNull
  @Override
  public String getName() {
    return this.keyEntries.get(Column.Group.NAME).getValue();
  }

  @Override
  public void setName(String name) {
    super.setWithKey(name, Column.Group.NAME);
  }

  @NotNull
  @Override
  public Integer getPriority() {
    return super.getFirstWithKey(Column.Group.PRIORITY);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.group.DbGroupBasis toLocal() {
    return new DbCachedSimpleGroup(this);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.group.DbGroupBasis toDatabase() {
    return this;
  }

}
