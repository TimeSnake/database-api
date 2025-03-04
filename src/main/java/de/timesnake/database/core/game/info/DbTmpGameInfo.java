/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbIntegerArrayList;
import de.timesnake.database.util.object.DbStringArrayList;
import de.timesnake.library.basic.util.Availability;
import de.timesnake.library.basic.util.DiscordChannelType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DbTmpGameInfo extends DbGameInfo implements
    de.timesnake.database.util.game.DbTmpGameInfo {

  public DbTmpGameInfo(DatabaseConnector databaseConnector, String nameTable, String gameName) {
    super(databaseConnector, nameTable, gameName);
  }

  @NotNull
  @Override
  public Integer getAutoStartPlayerNumber() {
    return super.getFirstWithKey(Column.Game.AUTO_START_PLAYER_NUMBER);
  }

  @Override
  public void setAutoStartPlayerNumber(Integer number) {
    super.setWithKey(number, Column.Game.AUTO_START_PLAYER_NUMBER);
  }

  @NotNull
  @Override
  public Integer getMinPlayerNumber() {
    return super.getFirstWithKey(Column.Game.MIN_PLAYER_NUMBER);
  }

  @Override
  public void setMinPlayerNumber(Integer number) {
    super.setWithKey(number, Column.Game.MIN_PLAYER_NUMBER);
  }

  @Override
  public boolean showSelectedKits() {
    return super.getFirstWithKey(Column.Game.SHOW_SELECTED_KITS);
  }

  @Override
  public void setShowSelectedKits(boolean showSelectedKits) {
    super.setWithKey(showSelectedKits, Column.Game.SHOW_SELECTED_KITS);
  }

  @Override
  public @NotNull List<Integer> getTeamAmounts() {
    return super.getFirstWithKey(Column.Game.TEAM_SIZES);
  }

  @Override
  public void setTeamAmounts(List<Integer> sizes) {
    super.setWithKey(new DbIntegerArrayList(sizes), Column.Game.TEAM_SIZES);
  }

  @NotNull
  @Override
  public Availability getTeamMergeAvailability() {
    Availability availability = super.getFirstWithKey(Column.Game.TEAM_MERGE);
    return availability != null ? availability : Availability.FORBIDDEN;
  }

  @Override
  public void setTeamMergeAvailability(Availability availability) {
    super.setWithKey(availability, Column.Game.TEAM_MERGE);
  }

  @Override
  public boolean isEqualTeamSizeRequired() {
    return super.getFirstWithKey(Column.Game.EQUAL_TEAM_SIZE_REQUIRED);
  }

  @Override
  public void requireEqualTeamSize(boolean require) {
    super.setWithKey(require, Column.Game.EQUAL_TEAM_SIZE_REQUIRED);
  }

  @Override
  public boolean hideTeams() {
    return super.getFirstWithKey(Column.Game.HIDE_TEAMS);
  }

  @Override
  public void setHideTeams(boolean hide) {
    super.setWithKey(hide, Column.Game.HIDE_TEAMS);
  }

  @NotNull
  @Override
  public DiscordChannelType getDiscordType() {
    DiscordChannelType discord = super.getFirstWithKey(Column.Game.DISCORD_TYPE);
    return discord != null ? discord : DiscordChannelType.FORBIDDEN;
  }

  @Override
  public void setDiscordType(DiscordChannelType type) {
    super.setWithKey(type, Column.Game.DISCORD_TYPE);
  }

  @Override
  public List<String> getDescription() {
    return super.getFirstWithKey(Column.Game.DESCRIPTION);
  }

  @Override
  public void setDescription(List<String> description) {
    super.setWithKey(new DbStringArrayList(description), Column.Game.DESCRIPTION);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTmpGameInfo toDatabase() {
    return this;
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbTmpGameInfo toLocal() {
    return new DbCachedTmpGameInfo(this);
  }
}
