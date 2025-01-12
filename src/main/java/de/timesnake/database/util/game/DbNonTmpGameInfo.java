/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import org.jetbrains.annotations.NotNull;

public interface DbNonTmpGameInfo extends DbGameInfo {

  boolean isCreationRequestable();

  @NotCached
  void setCreationRequestable(Boolean creationRequestable);

  boolean isOwnable();

  @NotCached
  void setOwnable(Boolean ownable);

  @NotNull
  @Override
  DbNonTmpGameInfo toDatabase();

  @NotNull
  @Override
  DbNonTmpGameInfo toLocal();
}
