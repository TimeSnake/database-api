/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbLocation;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface DatabaseLounges {

  void addMap(String name, DbLocation spawn);

  void removeMap(String name);

  boolean containsMap(String name);

  @NotNull
  DbLoungeMap getMap(String name);

  @NotNull
  Collection<DbLoungeMap> getMaps();

  @NotNull
  Collection<DbLoungeMap> getCachedMaps();
}
