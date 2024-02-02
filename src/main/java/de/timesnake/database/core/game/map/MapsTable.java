/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.DatabaseManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MapsTable {

  private final String gameName;

  private final MapsInfoTable infoTable;
  private final MapsLocationTable spawnsTable;
  private final MapsAuthorTable authorTable;
  private final MapsPropertyTable propertyTable;

  public MapsTable(String gameName) {
    this.infoTable = DatabaseManager.getInstance().getGameMaps().getMapsInfoTable(gameName);
    this.spawnsTable = DatabaseManager.getInstance().getGameMaps().getLocationTable(gameName);
    this.authorTable = DatabaseManager.getInstance().getGameMaps().getMapsAuthorTable(gameName);
    this.propertyTable = DatabaseManager.getInstance().getGameMaps().getMapsPropertyTable(gameName);
    this.gameName = gameName;
  }

  public void create() {
    this.infoTable.create();
    this.spawnsTable.create();
    this.authorTable.create();
    this.propertyTable.create();
  }

  public void backup() {
    this.infoTable.backup();
    this.spawnsTable.backup();
    this.authorTable.backup();
    this.propertyTable.backup();
  }

  public void delete() {
    this.infoTable.delete();
    this.spawnsTable.delete();
    this.authorTable.delete();
    this.propertyTable.delete();
  }

  public void addMap(String name) {
    this.infoTable.addMapInfo(name);
  }

  public void removeMap(String name) {
    this.infoTable.removeMapInfo(name);
    this.spawnsTable.deleteMap(name);
  }

  @NotNull
  public de.timesnake.database.util.game.DbMap getMap(String mapName) {
    return new DbMap(gameName, mapName);
  }

  public List<de.timesnake.database.util.game.DbMap> getMaps() {
    List<de.timesnake.database.util.game.DbMap> maps = new ArrayList<>();
    for (DbMapInfo info : this.infoTable.getMaps()) {
      maps.add(this.getMap(info.getName()));
    }
    return maps;
  }

  public List<de.timesnake.database.util.game.DbMap> getMaps(Integer players) {
    List<de.timesnake.database.util.game.DbMap> maps = new ArrayList<>();
    for (DbMapInfo info : this.infoTable.getMaps(players)) {
      maps.add(this.getMap(info.getName()));
    }
    return maps;
  }

  public boolean containsMap(String mapName) {
    return this.infoTable.containsMapInfo(mapName);
  }

  public Set<String> getMapNames() {
    return this.infoTable.getMapNames();
  }
}
