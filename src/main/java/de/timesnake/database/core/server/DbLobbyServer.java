/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.ServerType;
import org.jetbrains.annotations.NotNull;

public class DbLobbyServer extends DbServer implements
    de.timesnake.database.util.server.DbLobbyServer {

  public DbLobbyServer(DatabaseConnector databaseConnector, String name, String nameTable) {
    super(databaseConnector, name, nameTable);
  }

  @NotNull
  @Override
  public ServerType getType() {
    return ServerType.LOBBY;
  }
}
