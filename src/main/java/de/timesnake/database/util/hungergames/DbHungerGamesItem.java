/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.hungergames;

import org.jetbrains.annotations.NotNull;

public interface DbHungerGamesItem {

  boolean exists();

  @NotNull
  String getType();

  @NotNull
  Integer getAmount();

  @NotNull
  Float getChance();

  @NotNull
  Integer getLevel();
}
