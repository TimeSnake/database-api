/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DbMapInfo extends TableQuery {

  protected DbMapInfo(DatabaseConnector databaseConnector, String nameTable, String mapName) {
    super(databaseConnector, nameTable, new Entry<>(mapName, Column.Game.MAP_NAME));
  }

  @NotNull
  public String getName() {
    return (String) super.primaryEntries.get(0).getValue();
  }

  public boolean exists() {
    return super.getFirstWithKey(Column.Game.MAP_NAME) != null;
  }

  @NotNull
  public String getDisplayName() {
    return super.getFirstWithKey(Column.Game.MAP_DISPLAY_NAME);
  }

  public void setDisplayName(String displayName) {
    super.setWithKey(displayName, Column.Game.MAP_DISPLAY_NAME);
  }

  @Nullable
  public Integer getMinPlayers() {
    return super.getFirstWithKey(Column.Game.MAP_MIN_PLAYERS);
  }

  public void setMinPlayers(Integer minPlayers) {
    super.setWithKey(minPlayers, Column.Game.MAP_MIN_PLAYERS);
  }

  @Nullable
  public Integer getMaxPlayers() {
    return super.getFirstWithKey(Column.Game.MAP_MAX_PLAYERS);
  }

  public void setMaxPlayers(Integer maxPlayers) {
    super.setWithKey(maxPlayers, Column.Game.MAP_MAX_PLAYERS);
  }

  @NotNull
  public List<Integer> getTeamAmounts() {
    return super.getFirstWithKey(Column.Game.MAP_TEAM_AMOUNTS);
  }

  public void setTeamAmounts(List<Integer> teamAmounts) {
    super.setWithKey(teamAmounts, Column.Game.MAP_TEAM_AMOUNTS);
  }

  @Nullable
  public String getItemName() {
    return super.getFirstWithKey(Column.Game.MAP_ITEM);
  }

  public void setItemName(String itemName) {
    this.setWithKey(itemName, Column.Game.MAP_ITEM);
  }

  @NotNull
  public List<String> getDescription() {
    return super.getFirstWithKey(Column.Game.MAP_DESCRIPTION);
  }

  public void setDescription(List<String> description) {
    this.setWithKey(description, Column.Game.MAP_DESCRIPTION);
  }

  public boolean isEnabled() {
    return super.getFirstWithKey(Column.Game.MAP_ENABLE);
  }

  public void setEnabled(boolean enable) {
    super.setWithKey(enable, Column.Game.MAP_ENABLE);
  }

}
