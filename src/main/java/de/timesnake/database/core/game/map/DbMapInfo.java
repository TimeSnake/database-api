/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.DatabaseConnector;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

  @Nullable
  public String getItemName() {
    return super.getFirstWithKey(Column.Game.MAP_ITEM);
  }

  public void setItemName(String itemName) {
    this.setWithKey(itemName, Column.Game.MAP_ITEM);
  }

  public List<String> getDescription() {
    return super.getFirstWithKey(Column.Game.MAP_DESCRIPTION);
  }

  public void setDescription(List<String> description) {
    this.setWithKey(description, Column.Game.MAP_DESCRIPTION);
  }

  public List<String> getInfo() {
    return super.getFirstWithKey(Column.Game.MAP_INFO);
  }

  public void setInfo(List<String> info) {
    this.setWithKey(info, Column.Game.MAP_INFO);
  }

  public boolean isEnabled() {
    return super.getFirstWithKey(Column.Game.MAP_ENABLE);
  }

  public void setEnabled(boolean enable) {
    super.setWithKey(enable, Column.Game.MAP_ENABLE);
  }

}
