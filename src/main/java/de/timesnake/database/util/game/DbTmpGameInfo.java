/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.database.util.object.NotCached;
import de.timesnake.library.basic.util.Availability;
import de.timesnake.library.basic.util.DiscordChannelType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DbTmpGameInfo extends DbGameInfo {

  @NotNull
  Integer getAutoStartPlayerNumber();

  @NotCached
  void setAutoStartPlayerNumber(Integer number);

  @NotNull
  Integer getMinPlayerNumber();

  @NotCached
  void setMinPlayerNumber(Integer number);

  boolean showSelectedKits();

  @NotCached
  void setShowSelectedKits(boolean showSelectedKits);

  @NotNull
  List<Integer> getTeamAmounts();

  @NotCached
  void setTeamAmounts(List<Integer> sizes);

  @NotNull
  Availability getTeamMergeAvailability();

  @NotCached
  void setTeamMergeAvailability(Availability availability);

  boolean isEqualTeamSizeRequired();

  @NotCached
  void requireEqualTeamSize(boolean require);

  boolean hideTeams();

  @NotCached
  void setHideTeams(boolean hide);

  @NotNull
  DiscordChannelType getDiscordType();

  void setDiscordType(DiscordChannelType type);

  @Nullable
  List<String> getDescription();

  @NotCached
  void setDescription(List<String> description);

  @NotNull
  @Override
  DbTmpGameInfo toDatabase();

  @NotNull
  @Override
  DbTmpGameInfo toLocal();
}
