/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MapPropertyTable extends DefinitionAndQueryTool {

  public MapPropertyTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.GAME_NAME, Column.Game.MAP_NAME, Column.Game.MAP_PROPERTY_KEY);
    super.addColumn(Column.Game.MAP_PROPERTY_VALUE);
    this.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  public void removeProperty(@NotNull String gameName, @NotNull String mapName, @NotNull String key) {
    this.deleteEntry(
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(mapName, Column.Game.MAP_NAME),
        new Entry<>(key, Column.Game.MAP_PROPERTY_KEY));
  }

  public @Nullable String getProperty(@NotNull String gameName, @NotNull String mapName, @NotNull String key) {
    return this.getFirst(Column.Game.MAP_PROPERTY_VALUE,
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(mapName, Column.Game.MAP_NAME),
        new Entry<>(key, Column.Game.MAP_PROPERTY_KEY));
  }

  public void setProperty(@NotNull String gameName, @NotNull String mapName, @NotNull String key,
                          @Nullable String value) {
    this.set(value, Column.Game.MAP_PROPERTY_VALUE,
        new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(mapName, Column.Game.MAP_NAME),
        new Entry<>(key, Column.Game.MAP_PROPERTY_KEY));
  }

  public @NotNull Map<String, String> getProperties(@NotNull String gameName, @NotNull String mapName) {
    return this.get(Set.of(Column.Game.MAP_PROPERTY_KEY, Column.Game.MAP_PROPERTY_VALUE),
            new Entry<>(gameName, Column.Game.GAME_NAME), new Entry<>(mapName, Column.Game.MAP_NAME)).stream()
        .collect(Collectors.toMap(c -> c.get(Column.Game.MAP_PROPERTY_KEY),
            c -> c.get(Column.Game.MAP_PROPERTY_VALUE)));
  }

  public void removeProperties(String gameName, String name) {
    this.deleteEntry(new Entry<>(gameName, Column.Game.GAME_NAME),
        new Entry<>(name, Column.Game.MAP_NAME));
  }
}
