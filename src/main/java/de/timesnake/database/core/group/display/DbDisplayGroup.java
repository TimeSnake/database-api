/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group.display;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.group.DbGroup;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DbDisplayGroup extends DbGroup implements de.timesnake.database.util.group.DbDisplayGroup {

  public DbDisplayGroup(DatabaseConnector databaseConnector, String name, String nameTable) {
    super(databaseConnector, name, nameTable);
  }

  @Override
  public boolean showAlways() {
    return super.getFirstWithKey(Column.Group.SHOW_ALWAYS);
  }

  @Override
  public void setShowAlways(boolean showAlways) {
    super.setWithKey(showAlways, Column.Group.SHOW_ALWAYS);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.group.DbDisplayGroup toLocal() {
    return new DbCachedDisplayGroup(this);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.group.DbDisplayGroup toDatabase() {
    return this;
  }

}
