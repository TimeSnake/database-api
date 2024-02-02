/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseMaps extends DatabaseConnector {

  private final Map<String, MapsInfoTable> infoTables = new ConcurrentHashMap<>();
  private final Map<String, MapsLocationTable> locationTables = new ConcurrentHashMap<>();
  private final Map<String, MapsAuthorTable> authorTables = new ConcurrentHashMap<>();
  private final Map<String, MapsPropertyTable> propertyTables = new ConcurrentHashMap<>();

  private final String infoTableName;
  private final String locationTableName;
  private final String authorTableName;
  private final String propertyTableName;

  public DatabaseMaps(String name, String url, String options, String user, String password,
                      String infoTableName, String locationTableName, String authorTableName,
                      String propertyTableName) {
    super(name, url, options, user, password);
    this.infoTableName = infoTableName;
    this.locationTableName = locationTableName;
    this.authorTableName = authorTableName;
    this.propertyTableName = propertyTableName;
  }

  @NotNull
  public MapsInfoTable getMapsInfoTable(String gameName) {
    return this.infoTables.computeIfAbsent(gameName, n ->
        new MapsInfoTable(this, n + "_" + this.infoTableName));
  }

  @NotNull
  public MapsLocationTable getLocationTable(String gameName) {
    return this.locationTables.computeIfAbsent(gameName, n ->
        new MapsLocationTable(this, n + "_" + this.locationTableName));
  }

  @NotNull
  public MapsAuthorTable getMapsAuthorTable(String gameName) {
    return this.authorTables.computeIfAbsent(gameName, n ->
        new MapsAuthorTable(this, n + "_" + this.authorTableName));
  }

  @NotNull
  public MapsPropertyTable getMapsPropertyTable(String gameName) {
    return this.propertyTables.computeIfAbsent(gameName, n ->
        new MapsPropertyTable(this, n + "_" + this.propertyTableName));
  }
}
