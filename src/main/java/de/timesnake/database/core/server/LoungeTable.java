/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

public class LoungeTable extends TaskTable<DbLoungeServer> {

  public LoungeTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable);
  }

  @Nullable
  @Override
  public DbLoungeServer getServer(String name) {
    DbLoungeServer server = new DbLoungeServer(this.databaseConnector, name, this.tableName);
    return server.exists() ? server : null;
  }
}
