/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.server;

import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public interface DbNonTmpGameServer extends DbTaskServer {

  @Nullable
  String getGameInfo();

  void setGameInfo(String info);

  @Nullable
  UUID getOwnerUuid();

  void setOwnerUuid(UUID uuid);

  boolean hasOwner();
}
