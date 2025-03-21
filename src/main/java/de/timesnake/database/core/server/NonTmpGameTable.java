/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

public class NonTmpGameTable extends PvPTable<DbNonTmpGameServer> {


  public NonTmpGameTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable);
    super.addColumn(Column.Server.GAME_INFO);
    super.addColumn(Column.Server.OWNER);
  }

  @Nullable
  @Override
  public DbNonTmpGameServer getServer(String name) {
    DbNonTmpGameServer server = new DbNonTmpGameServer(this.databaseConnector, name,
        this.tableName);
    return server.exists() ? server : null;
  }
}
