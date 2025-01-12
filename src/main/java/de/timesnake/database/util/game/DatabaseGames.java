/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.util.object.DbLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DatabaseGames {

  @Nullable
  DbGame getGame(String gameName);

  boolean containsGame(String gameName);

  Collection<String> getGamesName();

  Collection<DbGame> getGames();

  void addLoungeMap(String name, DbLocation spawn);

  void removeLoungeMap(String name);

  boolean containsLoungeMap(String name);

  @NotNull
  DbLoungeMap getLoungeMap(String name);

  @NotNull
  Collection<DbLoungeMap> getLoungeMaps();

  @NotNull
  Collection<DbLoungeMap> getCachedLoungeMaps();
}
