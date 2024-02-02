/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.game.DbGame;
import de.timesnake.database.util.game.DbMap;
import de.timesnake.database.util.object.DbLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DbCachedMap extends DbCachedMapInfo implements DbMap {

  private final de.timesnake.database.core.game.map.DbMap map;

  public DbCachedMap(de.timesnake.database.core.game.map.DbMap map) {
    super(map.getDbInfo());
    this.map = map;
  }

  @Override
  public void delete() {
    this.map.delete();
  }

  @NotNull
  @Override
  public String getWorldName() {
    return this.map.getWorldName();
  }

  @NotNull
  @Override
  public DbGame getGame() {
    return this.map.getGame();
  }

  @Nullable
  @Override
  public DbLocation getLocation(Integer number) {
    return this.map.getLocation(number);
  }

  @Nullable
  @Override
  public DbLocation getFirstLocation() {
    return this.map.getFirstLocation();
  }

  @Nullable
  @Override
  public Integer getFirstLocationNumber() {
    return this.map.getFirstLocationNumber();
  }

  @Nullable
  @Override
  public DbLocation getLastLocation() {
    return this.map.getLastLocation();
  }

  @Nullable
  @Override
  public Integer getLastLocationNumber() {
    return this.map.getLastLocationNumber();
  }

  @NotNull
  @Override
  public HashMap<Integer, DbLocation> getMapLocations() {
    return this.map.getMapLocations();
  }

  @Override
  public void setLocation(Integer number, DbLocation location) {
    this.map.setLocation(number, location);
  }

  @Override
  public void deleteLocation(Integer number) {
    this.map.deleteLocation(number);
  }

  @Override
  public boolean containsLocation(Integer number) {
    return this.map.containsLocation(number);
  }

  @Override
  public @NotNull Map<String, String> getProperties() {
    return this.map.getProperties();
  }

  @Override
  public @Nullable String getProperty(@NotNull String key) {
    return this.map.getProperty(key);
  }

  @Override
  public void setProperty(@NotNull String key, @Nullable String value) {
    this.map.setProperty(key, value);
  }

  @NotNull
  @Override
  public List<UUID> getAuthors() {
    return this.map.getAuthors();
  }

  @Override
  public void setAuthors(List<UUID> authors) {
    this.map.setAuthors(authors);
  }

  @Override
  public void addAuthor(UUID author) {
    this.map.addAuthor(author);
  }

  @NotNull
  @Override
  public List<String> getAuthorNames() {
    return this.map.getAuthorNames();
  }

  @Override
  public void setAuthorNames(List<String> authors) {
    this.map.setAuthorNames(authors);
  }

  @Override
  public void removeAuthor(UUID author) {
    this.map.removeAuthor(author);
  }

  @NotNull
  @Override
  public DbMap toLocal() {
    return new DbCachedMap(this.map);
  }

  @NotNull
  @Override
  public DbMap toDatabase() {
    return this.map;
  }
}
