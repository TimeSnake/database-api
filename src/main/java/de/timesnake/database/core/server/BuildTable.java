/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

public class BuildTable extends TaskTable<DbBuildServer> {

  private final BuildWorldTable buildWorldTable;

  public BuildTable(DatabaseConnector databaseConnector, String nameTable,
      BuildWorldTable buildWorldTable) {
    super(databaseConnector, nameTable);
    this.buildWorldTable = buildWorldTable;
  }

  @Nullable
  @Override
  public DbBuildServer getServer(String name) {
    DbBuildServer server = new DbBuildServer(this.databaseConnector, name, this.tableName,
        this.buildWorldTable);
    return server.exists() ? server : null;
  }
}
