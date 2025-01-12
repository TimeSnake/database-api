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

  private final PermissionTable permissionTable;

  private final String permissionsTableName;

  public DatabasePermissions(String name, String url, String options, String user, String password,
      String permissionsTable) {
    super(name, url, options, user, password);
    this.permissionsTableName = permissionsTable;
    this.permissionTable = new PermissionTable(this, permissionsTableName);
  }

  @Override
  public void createTables() {
    permissionTable.create();
  }

  @Override
  public void saveTables() {
    permissionTable.save();
  }

  @Override
  public void addPermission(String name, String permission, Status.Permission mode) {
    this.permissionTable.addPermission(name, permission, mode);
  }

  @Override
  public void addPermission(String name, String permission, Status.Permission mode, SyncExecute syncExecute) {
    this.permissionTable.addPermission(name, permission, mode, syncExecute);
  }

  @Override
  public void deletePermission(String name, String permission) {
    this.permissionTable.removePermission(name, permission);
  }

  @Override
  public void deletePermission(String name, String permission, SyncExecute syncExecute) {
    this.permissionTable.removePermission(name, permission, syncExecute);
  }

  @Override
  public boolean containsPermission(String name, String permission) {
    return this.permissionTable.containsPermission(name, permission);
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
    if (this.permissionTable.getIdFromName(name, permission) != 0) {
      return new DbPermission(this, this.permissionTable.getIdFromName(name, permission),
          this.permissionsTableName);
    }
    return null;
  }

  @NotNull
  @Override
  public Collection<de.timesnake.database.util.permission.DbPermission> getCachedPermissions(String name) {
    return this.permissionTable.getCachedPermissions(name);
  }
}
