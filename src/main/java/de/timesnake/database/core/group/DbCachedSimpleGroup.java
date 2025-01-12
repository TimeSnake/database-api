/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.group;

import org.jetbrains.annotations.NotNull;

public class DbCachedSimpleGroup implements de.timesnake.database.util.group.DbGroupBasis {

  protected final DbSimpleGroup database;

  protected final int rank;
  protected String name;

  public DbCachedSimpleGroup(DbSimpleGroup database) {
    this.database = database;
    this.rank = this.database.getPriority();
  }

  public de.timesnake.database.util.group.DbGroupBasis getDatabase() {
    return database;
  }

  @Override
  public boolean exists() {
    return this.database.exists();
  }

  @NotNull
  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
    this.database.setName(name);
  }

  @NotNull
  @Override
  public Integer getPriority() {
    return this.rank;
  }

  @NotNull
  @Override
  public de.timesnake.database.util.group.DbGroupBasis toLocal() {
    return this.database.toLocal();
  }

  @NotNull
  @Override
  public de.timesnake.database.util.group.DbGroupBasis toDatabase() {
    return this.database;
  }
}
