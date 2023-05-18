/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.network;

import java.io.File;
import org.jetbrains.annotations.NotNull;

public interface DbNetworkFile {

  boolean exists();

  @NotNull
  File getFile();
}
