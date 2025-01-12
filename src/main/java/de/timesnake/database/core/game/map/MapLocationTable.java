/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.LocationTable;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MapLocationTable extends LocationTable {

  public MapLocationTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.GAME_NAME, Column.Game.MAP_NAME);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  @Override
  public void delete() {
    super.delete();
  }

  public ArrayList<DbMapLocations> getMaps(String gameName) {
    ArrayList<DbMapLocations> maps = new ArrayList<>();
    ArrayList<String> mapNames = new ArrayList<>();
    for (String mapName : super.get(Column.Game.MAP_NAME, new Entry<>(gameName, Column.Game.GAME_NAME))) {
      DbMapLocations map = this.getMapLocations(gameName, mapName);
      if (!mapNames.contains(mapName)) {
        maps.add(map);
        mapNames.add(mapName);
      }
    }
    return maps;
  }

  public boolean containsMap(String gameName, String mapName) {
    return super.get(Column.Game.MAP_NAME,
            new Entry<>(gameName, Column.Game.GAME_NAME),
            new Entry<>(mapName, Column.Game.MAP_NAME))
        .contains(mapName);
  }

  @NotNull
  public DbMapLocations getMapLocations(String gameName, String mapName) {
    return new DbMapLocations(this.databaseConnector, this.tableName, gameName, mapName);
  }

  public void removeMapLocations(String gameName, String mapName) {
    super.deleteEntry(new Entry<>(gameName, Column.Game.GAME_NAME), new Entry<>(mapName, Column.Game.MAP_NAME));
  }
}
