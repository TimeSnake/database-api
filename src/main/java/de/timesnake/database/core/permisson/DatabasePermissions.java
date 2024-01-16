/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.permisson;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DatabasePermissions extends DatabaseConnector implements
    de.timesnake.database.util.permission.DatabasePermissions {

  private final PermissionsTable permissionsTable;

  private final String permissionsTableName;

  public DatabasePermissions(String name, String url, String options, String user, String password,
      String permissionsTable) {
    super(name, url, options, user, password);
    this.permissionsTableName = permissionsTable;
    this.permissionsTable = new PermissionsTable(this, permissionsTableName);
  }

  @Override
  public void createTables() {
    permissionsTable.create();
  }

  @Override
  public void backupTables() {
    permissionsTable.backup();
  }

  @Override
  public void addPermission(String name, String permission, Status.Permission mode) {
    this.permissionsTable.addPermission(name, permission, mode);
  }

  @Override
  public void addPermission(String name, String permission, Status.Permission mode, SyncExecute syncExecute) {
    this.permissionsTable.addPermission(name, permission, mode, syncExecute);
  }

  @Override
  public void deletePermission(String name, String permission) {
    this.permissionsTable.removePermission(name, permission);
  }

  @Override
  public void deletePermission(String name, String permission, SyncExecute syncExecute) {
    this.permissionsTable.removePermission(name, permission, syncExecute);
  }

  @Override
  public boolean containsPermission(String name, String permission) {
    return this.permissionsTable.containsPermission(name, permission);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.permission.DbPermission getPermission(Integer id) {
    return new DbPermission(this, id, this.permissionsTableName);
  }

  @Nullable
  @Override
  @Deprecated
  public de.timesnake.database.util.permission.DbPermission getPermission(String name, String permission) {
    if (this.permissionsTable.getIdFromName(name, permission) != 0) {
      return new DbPermission(this, this.permissionsTable.getIdFromName(name, permission),
          this.permissionsTableName);
    }
    return null;
  }

  @NotNull
  @Override
  public Collection<de.timesnake.database.util.permission.DbPermission> getCachedPermissions(String name) {
    return this.permissionsTable.getCachedPermissions(name);
  }
}
