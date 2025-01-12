/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DbMapInfo extends KeyedQueryTool {

  protected DbMapInfo(DatabaseConnector databaseConnector, String nameTable, String gameName, String mapName) {
    super(databaseConnector, nameTable, true,
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(mapName, Column.Game.MAP_NAME));
  }

  @NotNull
  public String getName() {
    return super.keyEntries.get(Column.Game.MAP_NAME).getValue();
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
