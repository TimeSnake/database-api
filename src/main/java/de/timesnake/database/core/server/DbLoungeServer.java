/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.server.DbServer;
import de.timesnake.database.util.server.DbTmpGameServer;
import de.timesnake.library.basic.util.ServerType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbLoungeServer extends DbTaskServer implements
    de.timesnake.database.util.server.DbLoungeServer {

  public DbLoungeServer(DatabaseConnector databaseConnector, String name, String nameTable) {
    super(databaseConnector, name, nameTable);
  }

  @Nullable
  @Override
  public DbTmpGameServer getTwinServer() {
    ServerTable<? extends DbServer> table = ((DatabaseServers) Database.getServers()).getServerTables()
        .get(ServerType.TEMP_GAME);
    if (table != null) {
      return ((TmpGameTable) table).getServerByTwinServerName(this.getName());
    }
    return null;
  }

  @NotNull
  @Override
  public ServerType getType() {
    return ServerType.LOUNGE;
  }
}
