/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.permission;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;

public interface DbPermission extends DbCached<DbPermission> {

  boolean exists();

  @NotNull
  Integer getId();

  @NotNull
  String getPermission();

  @NotCached
  void setPermission(String permission);

  @NotNull
  Status.Permission getMode();

  @NotCached
  void setMode(Status.Permission mode);
}
