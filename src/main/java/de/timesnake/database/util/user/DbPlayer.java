/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.user;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public interface DbPlayer {

  boolean exists();

  /**
   * Gets the {@link DbPlayer}-name
   *
   * @return the name
   */
  @NotNull
  String getName();

  /**
   * Gets the {@link UUID} of the {@link DbPlayer}
   *
   * @return the {@link UUID}
   */
  @NotNull
  UUID getUniqueId();
}
