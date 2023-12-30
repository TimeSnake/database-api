/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.server;

import de.timesnake.library.basic.util.ServerType;
import org.jetbrains.annotations.NotNull;

public interface DbLobbyServer extends DbServer {

  @NotNull
  @Override
  ServerType getType();
}
