/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.server;

import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.ServerType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface DbBuildServer extends DbTaskServer {

  @NotNull
  @Override
  ServerType getType();

  @NotNull
  Collection<String> getWorldNames();

  void addWorld(String worldName);

  void removeWorld(String worldName);

  void clearWorlds();

  void clearWorlds(SyncExecute syncExecute);
}
