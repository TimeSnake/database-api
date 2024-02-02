/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DbStringArrayList;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class DbCachedMapInfo {

  protected final DbMapInfo mapInfo;

  private final String name;
  private String displayName;
  private Integer minPlayers;
  private Integer maxPlayers;
  private List<Integer> teamAmounts;
  private String itemName;
  private List<String> description;
  private boolean enabled;

  public DbCachedMapInfo(DbMapInfo mapInfo) {
    this.mapInfo = mapInfo;

    this.name = mapInfo.getName();

    ColumnMap columnMap = mapInfo.getFirstWithKey(Set.of(Column.Game.MAP_DISPLAY_NAME,
        Column.Game.MAP_MIN_PLAYERS, Column.Game.MAP_MAX_PLAYERS, Column.Game.MAP_TEAM_AMOUNTS,
        Column.Game.MAP_ITEM, Column.Game.MAP_DESCRIPTION, Column.Game.MAP_ENABLE));

    this.displayName = columnMap.get(Column.Game.MAP_DISPLAY_NAME);
    this.minPlayers = columnMap.get(Column.Game.MAP_MIN_PLAYERS);
    this.maxPlayers = columnMap.get(Column.Game.MAP_MAX_PLAYERS);
    this.teamAmounts = columnMap.get(Column.Game.MAP_TEAM_AMOUNTS);
    this.itemName = columnMap.get(Column.Game.MAP_ITEM);
    this.description = columnMap.get(Column.Game.MAP_DESCRIPTION);
    this.enabled = columnMap.get(Column.Game.MAP_ENABLE);

  }

  @NotNull
  public String getName() {
    return this.name;
  }

  public boolean exists() {
    return this.mapInfo.exists();
  }

  @NotNull
  public String getDisplayName() {
    return this.displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
    this.mapInfo.setDisplayName(displayName);
  }

  @NotNull
  public Integer getMinPlayers() {
    return this.minPlayers;
  }

  public void setMinPlayers(Integer minPlayers) {
    this.minPlayers = minPlayers;
    this.mapInfo.setMinPlayers(minPlayers);
  }

  @NotNull
  public Integer getMaxPlayers() {
    return this.maxPlayers;
  }

  public void setMaxPlayers(Integer maxPlayers) {
    this.maxPlayers = maxPlayers;
    this.mapInfo.setMaxPlayers(maxPlayers);
  }

  @NotNull
  public List<Integer> getTeamAmounts() {
    return this.teamAmounts;
  }

  public void setTeamAmounts(List<Integer> teamAmounts) {
    this.teamAmounts = teamAmounts;
    this.mapInfo.setTeamAmounts(teamAmounts);
  }

  @NotNull
  public String getItemName() {
    return this.itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
    this.mapInfo.setItemName(itemName);
  }

  public @NotNull List<String> getDescription() {
    return this.description;
  }

  public void setDescription(List<String> description) {
    this.description = new DbStringArrayList(description);
    this.mapInfo.setDescription(description);
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enable) {
    this.enabled = enable;
    this.mapInfo.setEnabled(enabled);
  }

}