/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryKeyEntries;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class MapInfoTable extends DefinitionAndQueryTool {

  public MapInfoTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.GAME_NAME, Column.Game.MAP_NAME);
    super.addColumn(Column.Game.MAP_DISPLAY_NAME);
    super.addColumn(Column.Game.MAP_ITEM);
    super.addColumn(Column.Game.MAP_MIN_PLAYERS);
    super.addColumn(Column.Game.MAP_MAX_PLAYERS);
    super.addColumn(Column.Game.MAP_TEAM_AMOUNTS);
    super.addColumn(Column.Game.MAP_DESCRIPTION);
    super.addColumn(Column.Game.MAP_ENABLE);
  }

  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  public void delete() {
    super.delete();
  }

  public void addMapInfo(String gameName, String mapName) {
    super.addEntry(new PrimaryKeyEntries(new Entry<>(gameName, Column.Game.GAME_NAME),
            new Entry<>(mapName, Column.Game.MAP_NAME)),
        new Entry<>(true, Column.Game.MAP_ENABLE));
  }

  public void removeMapInfo(String gameName, String name) {
    super.deleteEntry(new Entry<>(gameName, Column.Game.GAME_NAME), new Entry<>(name, Column.Game.MAP_NAME));
  }

  @NotNull
  public DbMapInfo getMapInfo(String gameName, String name) {
    return new DbMapInfo(this.databaseConnector, this.tableName, gameName, name);
  }

  public boolean containsMapInfo(String gameName, String name) {
    return this.getMapInfo(gameName, name).exists();
  }

  public Collection<DbMapInfo> getMaps(String gameName) {
    ArrayList<DbMapInfo> maps = new ArrayList<>();
    ArrayList<String> mapNames = new ArrayList<>();
    for (String mapName : super.get(Column.Game.MAP_NAME, new Entry<>(gameName, Column.Game.GAME_NAME))) {
      DbMapInfo map = this.getMapInfo(gameName, mapName);
      if (!mapNames.contains(mapName)) {
        maps.add(map);
        mapNames.add(mapName);
      }
    }
    return maps;
  }

  public Collection<DbMapInfo> getMaps(String gameName, Integer players) {
    Collection<DbMapInfo> maps = this.getMaps(gameName);
    maps.removeIf(map -> (map.getMinPlayers() != null && map.getMinPlayers() > players)
        || (map.getMaxPlayers() != null && map.getMaxPlayers() < players));
    return maps;
  }

  public Set<String> getMapNames(String gameName) {
    return this.get(Column.Game.MAP_NAME, new Entry<>(gameName, Column.Game.GAME_NAME));
  }
}
