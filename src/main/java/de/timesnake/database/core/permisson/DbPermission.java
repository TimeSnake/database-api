/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.permisson;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;

public class DbPermission extends KeyedQueryTool implements de.timesnake.database.util.permission.DbPermission {

  public DbPermission(DatabaseConnector databaseConnector, int id, String nameTable) {
    super(databaseConnector, nameTable, true, new Entry<>(id, Column.Permission.ID));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Permission.ID) != null;
  }

  @NotNull
  @Override
  public Integer getId() {
    return super.keyEntries.get(Column.Permission.ID).getValue();
  }

  @NotNull
  @Override
  public String getPermission() {
    return super.getFirstWithKey(Column.Permission.PERMISSION);
  }

  @Override
  public void setPermission(String permission) {
    super.setWithKey(permission, Column.Permission.PERMISSION);
  }

  @NotNull
  @Override
  public Status.Permission getMode() {
    return super.getFirstWithKey(Column.Permission.MODE);
  }

  @Override
  public void setMode(Status.Permission mode) {
    super.setWithKey(mode, Column.Permission.MODE);
  }

  @Override
  public de.timesnake.database.util.permission.@NotNull DbPermission toLocal() {
    return new DbCachedPermission(this);
  }

  @Override
  public de.timesnake.database.util.permission.@NotNull DbPermission toDatabase() {
    return this;
  }
}
