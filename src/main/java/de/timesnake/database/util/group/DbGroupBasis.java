/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.group;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;
import org.jetbrains.annotations.NotNull;

public interface DbGroupBasis extends DbCached<DbGroupBasis> {

  @NotCached
  boolean exists();

  @NotNull
  String getName();

  @NotCached
  void setName(String name);

  @NotNull
  Integer getPriority();

}
