/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.user;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class DisplayGroupTable extends DefinitionAndQueryTool {

  public DisplayGroupTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.User.UUID, Column.User.DISPLAY_GROUP);
  }

  @Override
  protected void create() {
    super.create();
  }

  @Override
  protected void save() {
    super.save();
  }

  @NotNull
  public DbDisplayGroupUser getUser(UUID uuid) {
    return new DbDisplayGroupUser(this.databaseConnector, this.tableName, uuid);
  }
}
