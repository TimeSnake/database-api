/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class MapsInfoTable extends TableDDL {

  protected MapsInfoTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.MAP_NAME);
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
  public void backup() {
    super.backup();
  }

  public void delete() {
    super.delete();
  }

  public void addMapInfo(String name) {
    super.addEntry(new PrimaryEntries(new Entry<>(name, Column.Game.MAP_NAME)),
        new Entry<>(true, Column.Game.MAP_ENABLE));
  }

  public void removeMapInfo(String name) {
    super.deleteEntry(new Entry<>(name, Column.Game.MAP_NAME));
  }

  @NotNull
  public DbMapInfo getMapInfo(String name) {
    return new DbMapInfo(this.databaseConnector, this.tableName, name);
  }

  public boolean containsMapInfo(String name) {
    return this.getMapInfo(name).exists();
  }

  public Collection<DbMapInfo> getMaps() {
    ArrayList<DbMapInfo> maps = new ArrayList<>();
    ArrayList<String> mapNames = new ArrayList<>();
    for (String mapName : super.get(Column.Game.MAP_NAME)) {
      DbMapInfo map = this.getMapInfo(mapName);
      if (!mapNames.contains(mapName)) {
        maps.add(map);
        mapNames.add(mapName);
      }
    }
    return maps;
  }

  public Collection<DbMapInfo> getMaps(Integer players) {
    Collection<DbMapInfo> maps = this.getMaps();
    maps.removeIf(map -> (map.getMinPlayers() != null && map.getMinPlayers() > players)
        || (map.getMaxPlayers() != null && map.getMaxPlayers() < players));
    return maps;
  }

  public Set<String> getMapNames() {
    return this.get(Column.Game.MAP_NAME);
  }
}
