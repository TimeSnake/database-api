/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface DbPlayer {

  boolean exists();

  @Nullable
  String getName();

  @NotNull
  UUID getUniqueId();
}
