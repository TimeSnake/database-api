/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

public class TmpGameTable extends PvPTable<DbTmpGameServer> {

  public TmpGameTable(DatabaseConnector databaseConnector, String nameTable) {
    super(databaseConnector, nameTable);
    super.addColumn(Column.Server.MAP_NAME);
    super.addColumn(Column.Server.MAPS);
    super.addColumn(Column.Server.KITS);
    super.addColumn(Column.Server.TEAM_AMOUNT);
    super.addColumn(Column.Server.TEAM_MAX_PLAYERS);
    super.addColumn(Column.Server.TEAM_MERGING);
    super.addColumn(Column.Server.TWIN_SERVER);
    super.addColumn(Column.Server.DISCORD);
  }

  @Override
  public void backup() {
    super.dropTmpTable();
  }

  @Nullable
  @Override
  public DbTmpGameServer getServer(String name) {
    DbTmpGameServer server = new DbTmpGameServer(this.databaseConnector, name, this.tableName);
    return server.exists() ? server : null;
  }

  @Nullable
  public DbTmpGameServer getServerByTwinServerName(String twinServerName) {
    String name = this.getFirst(Column.Server.NAME,
        new Entry<>(twinServerName, Column.Server.TWIN_SERVER));
    if (name == null) {
      return null;
    }
    DbTmpGameServer server = new DbTmpGameServer(this.databaseConnector, name, this.tableName);
    return server.exists() ? server : null;
  }

}
