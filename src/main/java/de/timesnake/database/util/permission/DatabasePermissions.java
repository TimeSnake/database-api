/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.permission;

import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DatabasePermissions {

  void addPermission(String name, String permission, Status.Permission mode);

  void addPermission(String name, String permission, Status.Permission mode, SyncExecute syncExecute);

  void deletePermission(String name, String permission);

  void deletePermission(String name, String permission, SyncExecute syncExecute);

  boolean containsPermission(String name, String permission);

  @NotNull
  DbPermission getPermission(Integer id);

  @Deprecated
  @Nullable
  DbPermission getPermission(String name, String permission);

  @NotNull
  Collection<DbPermission> getCachedPermissions(String name);
}
