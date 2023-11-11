/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.DbLocation;
import de.timesnake.database.util.object.NotCached;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface DbMap extends DbCached<DbMap> {

  boolean exists();

  @NotCached
  void delete();

  @NotNull
  String getName();

  @NotNull
  String getDisplayName();

  @NotCached
  void setDisplayName(String displayName);

  @Nullable
  Integer getMinPlayers();

  @Nullable
  Integer getMaxPlayers();

  @NotNull
  List<Integer> getTeamAmounts();

  @NotNull
  String getWorldName();

  @NotCached
  @NotNull
  DbGame getGame();

  @Nullable
  @NotCached
  DbLocation getLocation(Integer number);

  @Nullable
  @NotCached
  DbLocation getFirstLocation();

  @Nullable
  @NotCached
  Integer getFirstLocationNumber();

  @Nullable
  @NotCached
  DbLocation getLastLocation();

  @Nullable
  @NotCached
  Integer getLastLocationNumber();

  @NotCached
  @NotNull
  HashMap<Integer, DbLocation> getMapLocations();

  @NotCached
  void addLocation(Integer number, DbLocation location);

  @NotCached
  void deleteLocation(Integer number);

  @NotCached
  boolean containsLocation(Integer number);

  @Nullable
  String getItemName();

  @NotNull
  List<String> getDescription();

  @NotNull
  List<String> getInfo();

  @NotCached
  void setInfo(List<String> info);

  boolean isEnabled();

  @NotCached
  void setEnabled(boolean enable);

  @NotCached
  @NotNull
  List<UUID> getAuthors();

  @NotCached
  void setAuthors(List<UUID> authors);

  @NotCached
  void addAuthor(UUID author);

  @NotCached
  void removeAuthor(UUID author);

  @NotCached
  @NotNull
  List<String> getAuthorNames();

  @NotCached
  void setAuthorNames(List<String> authors);
}
