/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.network;

import de.timesnake.database.core.network.DbNetworkFile;
import java.io.File;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DatabaseNetwork {

  void addNetworkFile(String name, File filePath);

  @Nullable
  DbNetworkFile getNetworkFile(String name);

  @NotNull
  List<DbNetworkFile> getNetworkFiles();

  void setValue(String key, String value);

  @Nullable
  String getValue(String key);
}
