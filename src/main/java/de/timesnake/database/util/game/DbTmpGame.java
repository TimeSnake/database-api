/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.DbCached;
import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.chat.ExTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface DbTmpGame extends DbGame, DbTmpGameInfo, DbCached<DbTmpGame> {

  @NotNull
  @Override
  DbTmpGameInfo getInfo();

  @NotCached
  void addTeam(String name, int rank, String prefix, ExTextColor color, float ratio,
      String colorName);

  @NotCached
  void removeTeam(String name);

  @Nullable
  @NotCached
  Integer getHighestRank();

  @NotCached
  boolean containsTeam(String name);

  @NotCached
  @Nullable
  de.timesnake.database.core.game.team.DbTeam getTeam(String name);

  @NotCached
  @NotNull
  Collection<String> getTeamNames();

  @NotCached
  @NotNull
  Collection<de.timesnake.database.util.game.DbTeam> getTeams();

}
