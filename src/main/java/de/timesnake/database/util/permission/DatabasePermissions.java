/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.permission;

import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.Status;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DatabasePermissions {

  void addPermission(String name, String permission, Status.Permission mode, String... servers);

  void addPermission(String name, String permission, Status.Permission mode,
      SyncExecute syncExecute,
      String... servers);

  void deletePermission(String name, String permission);

  void deletePermission(String name, String permission, SyncExecute syncExecute);

  boolean containsPermission(String name, String permission);

  @Nullable
  DbPermission getPermission(String name, String permission);

  @NotNull
  Collection<DbPermission> getPermissions(String name);
}
