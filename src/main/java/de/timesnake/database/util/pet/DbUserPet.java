/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.pet;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
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

  boolean isAllowForeignRiding();

  @NotCached
  void setAllowForeignRiding(boolean allowForeignRiding);

  @NotCached
  Map<@NotNull String, @Nullable String> getProperties();

  @NotCached
  @Nullable String getProperty(@NotNull String key);

  @NotCached
  void setProperty(@NotNull String key, @Nullable String value);

  @NotCached
  void removeProperties();

  @NotCached
  void removeProperty(@NotNull String key);
}
