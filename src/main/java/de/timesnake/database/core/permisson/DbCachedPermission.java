/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.permisson;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class DbCachedPermission implements de.timesnake.database.util.permission.DbPermission {

  private final DbPermission permission;

  private final Integer id;
  private String perm;
  private Status.Permission mode;

  public DbCachedPermission(DbPermission permission) {
    this.permission = permission;

    ColumnMap map = this.permission.getFirstWithKey(Set.of(Column.Permission.ID, Column.Permission.PERMISSION,
        Column.Permission.MODE));

    this.id = map.get(Column.Permission.ID);
    this.perm = map.get(Column.Permission.PERMISSION);
    this.mode = map.get(Column.Permission.MODE);
  }

  public DbCachedPermission(ColumnMap map) {
    this.id = map.get(Column.Permission.ID);
    this.perm = map.get(Column.Permission.PERMISSION);
    this.mode = map.get(Column.Permission.MODE);

    this.permission = (DbPermission) Database.getPermissions().getPermission(this.id);
  }

  @Override
  public boolean exists() {
    return this.permission.exists();
  }

  @NotNull
  @Override
  public Integer getId() {
    return id;
  }

  @NotNull
  @Override
  public String getPermission() {
    return perm;
  }

  @Override
  public void setPermission(String permission) {
    this.perm = permission;
    this.permission.setPermission(permission);
  }

  @NotNull
  @Override
  public Status.Permission getMode() {
    return mode;
  }

  @Override
  public void setMode(Status.Permission mode) {
    this.mode = mode;
    this.permission.setMode(mode);
  }

  @Override
  public de.timesnake.database.util.permission.@NotNull DbPermission toLocal() {
    return this.permission.toLocal();
  }

  @Override
  public de.timesnake.database.util.permission.@NotNull DbPermission toDatabase() {
    return this.permission;
  }
}
