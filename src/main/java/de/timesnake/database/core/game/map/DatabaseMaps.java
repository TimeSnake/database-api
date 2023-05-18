/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DatabaseMaps extends DatabaseConnector {

  private final String infoTableName;
  private final String spawnsTableName;
  private final String authorsTableName;

  public DatabaseMaps(String name, String url, String options, String user, String password,
      String infoTableName,
      String spawnsTableName, String authorsTableName) {
    super(name, url, options, user, password);
    this.infoTableName = infoTableName;
    this.spawnsTableName = spawnsTableName;
    this.authorsTableName = authorsTableName;
  }

  public void addGame(String gameName) {
    new MapLocationsTable(this, gameName).create();
  }

  public void deleteGameMaps(String gameName) {
    new MapLocationsTable(this, gameName).delete();
  }

  @NotNull
  public MapsInfoTable getMapsInfoTable(String gameName) {
    return new MapsInfoTable(this, gameName + "_" + this.infoTableName);
  }

  @NotNull
  public MapLocationsTable getMapsSpawnsTable(String gameName) {
    return new MapLocationsTable(this, gameName + "_" + this.spawnsTableName);
  }

  @NotNull
  public MapsAuthorTable getMapsAuthorTable(String gameName) {
    return new MapsAuthorTable(this, gameName + "_" + this.authorsTableName);
  }

}
