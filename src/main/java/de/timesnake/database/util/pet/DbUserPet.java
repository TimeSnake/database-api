/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.pet;

import de.timesnake.database.util.object.DbCached;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface DbUserPet extends DbCached<DbUserPet> {

  @NotNull
  UUID getOwnerId();

  @NotNull
  Integer getPetId();

  @NotNull
  String getPetType();

  void setPetType(@NotNull String type);

  boolean isEnabled();

  void setEnabled(boolean enabled);
}
