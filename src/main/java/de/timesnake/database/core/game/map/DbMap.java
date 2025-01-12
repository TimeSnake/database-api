/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.DatabaseManager;
import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DbLocation;
import de.timesnake.database.util.user.DbUser;
import de.timesnake.library.basic.util.server.Server;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class DbMap implements de.timesnake.database.util.game.DbMap {

  private final String gameName;

  private final String name;

  private final DbMapInfo info;
  private final DbMapLocations mapLocations;

  private final MapAuthorTable authorTable;
  private final MapPropertyTable mapPropertyTable;

  public DbMap(String gameName, String mapName, DbMapInfo info, DbMapLocations mapLocations,
               MapAuthorTable authorTable, MapPropertyTable mapPropertyTable) {
    this.gameName = gameName;
    this.name = mapName;

    this.info = info;
    this.mapLocations = mapLocations;
    this.authorTable = authorTable;
    this.mapPropertyTable = mapPropertyTable;
  }

  @Override
  public boolean exists() {
    return this.info.exists();
  }

  @Override
  public void delete() {
    this.info.delete();
    this.mapLocations.delete();
    this.authorTable.removeMapAuthors(this.gameName, this.name);
  }

  protected DbMapInfo getDbInfo() {
    return this.info;
  }

  @NotNull
  @Override
  public String getName() {
    return name;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return this.info.getDisplayName();
  }

  @Override
  public void setDisplayName(String displayName) {
    this.info.setDisplayName(displayName);
  }

  @Nullable
  @Override
  public Integer getMinPlayers() {
    return this.info.getMinPlayers();
  }

  @Override
  public void setMinPlayers(Integer minPlayers) {
    this.info.setMinPlayers(minPlayers);
  }

  @Nullable
  @Override
  public Integer getMaxPlayers() {
    return this.info.getMaxPlayers();
  }

  @Override
  public void setMaxPlayers(Integer maxPlayers) {
    this.info.setMaxPlayers(maxPlayers);
  }

  @Override
  @NotNull
  public List<Integer> getTeamAmounts() {
    return this.info.getTeamAmounts();
  }

  @Override
  public boolean isEnabled() {
    return this.info.isEnabled();
  }

  @Override
  public void setEnabled(boolean enable) {
    this.info.setEnabled(enable);
  }

  @NotNull
  @Override
  public String getWorldName() {
    return this.mapLocations.getWorldName();
  }

  @NotNull
  @Override
  public DbGame getGame() {
    return DatabaseManager.getInstance().getGames().getGame(this.gameName);
  }

  @Nullable
  @Override
  public DbLocation getLocation(Integer number) {
    return this.mapLocations.getLocation(number);
  }

  @Nullable
  @Override
  public DbLocation getFirstLocation() {
    return this.mapLocations.getFirstLocation();
  }

  @Nullable
  @Override
  public Integer getFirstLocationNumber() {
    return this.mapLocations.getFirstLocationNumber();
  }

  @Nullable
  @Override
  public DbLocation getLastLocation() {
    return this.mapLocations.getLastLocation();
  }

  @Nullable
  @Override
  public Integer getLastLocationNumber() {
    return this.mapLocations.getLastLocationNumber();
  }

  @NotNull
  @Override
  public HashMap<Integer, DbLocation> getMapLocations() {
    return this.mapLocations.getLocations();
  }

  @Override
  public void setLocation(Integer number, DbLocation location) {
    this.mapLocations.setLocation(number, location);
  }

  @Override
  public void deleteLocation(Integer number) {
    this.mapLocations.deleteLocation(number);
  }

  @Override
  public boolean containsLocation(Integer number) {
    return this.getLocation(number) != null;
  }

  @Nullable
  @Override
  public String getItemName() {
    return this.info.getItemName();
  }

  @Override
  public void setItemName(String name) {
    this.info.setItemName(name);
  }

  @NotNull
  @Override
  public List<String> getDescription() {
    return this.info.getDescription();
  }

  @Override
  @NotNull
  public Map<String, String> getProperties() {
    return this.mapPropertyTable.getProperties(this.gameName, this.name);
  }

  @Override
  @Nullable
  public String getProperty(@NotNull String key) {
    return this.mapPropertyTable.getProperty(this.gameName, this.name, key);
  }

  @Override
  public void setProperty(@NotNull String key, @Nullable String value) {
    this.mapPropertyTable.setProperty(this.gameName, this.name, key, value);
  }

  @NotNull
  @Override
  public List<UUID> getAuthors() {
    return this.authorTable.getAuthors(this.gameName, this.name).stream().map((DbMapAuthor::getAuthorUuid))
        .collect(Collectors.toList());
  }

  @Override
  public void setAuthors(List<UUID> authors) {
    authors.forEach(this::addAuthor);
  }

  @Override
  public void addAuthor(UUID author) {
    this.authorTable.addMapAuthor(this.gameName, this.name, author);
  }

  @Override
  public void removeAuthor(UUID author) {
    this.authorTable.removeMapAuthor(this.gameName, this.name, author);
  }

  @NotNull
  @Override
  public List<String> getAuthorNames() {
    List<String> authorNames = this.authorTable.getAuthors(this.gameName, this.name).stream()
        .map((DbMapAuthor::getAuthorName))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
    return !authorNames.isEmpty() ? authorNames : List.of(Server.DEFAULT_NETWORK_NAME + " Community");
  }

  @Override
  public void setAuthorNames(List<String> authors) {
    for (String name : authors) {
      DbUser user = Database.getUsers().getUser(name);
      if (user == null) {
        continue;
      }
      this.authorTable.addMapAuthor(this.gameName, this.name, user.getUniqueId());
    }
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbMap toLocal() {
    return new DbCachedMap(this);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbMap toDatabase() {
    return this;
  }
}
