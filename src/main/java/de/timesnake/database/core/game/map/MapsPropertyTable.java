/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapsPropertyTable extends TableDDL {

  protected MapsPropertyTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.MAP_NAME, Column.Game.MAP_PROPERTY_KEY);
    super.addColumn(Column.Game.MAP_PROPERTY_VALUE);
    this.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
  }

  @Override
  public void create() {
    super.create();
  }

  @Override
  public void backup() {
    super.backup();
  }

  @Override
  public void delete() {
    super.delete();
  }

  public void removeProperty(@NotNull String mapName, @NotNull String key) {
    this.deleteEntry(new Entry<>(mapName, Column.Game.MAP_NAME), new Entry<>(key, Column.Game.MAP_PROPERTY_KEY));
  }

  public @Nullable String getProperty(@NotNull String mapName, @NotNull String key) {
    return this.getFirst(Column.Game.MAP_PROPERTY_VALUE, new Entry<>(mapName, Column.Game.MAP_NAME),
        new Entry<>(key, Column.Game.MAP_PROPERTY_KEY));
  }

  public void setProperty(@NotNull String mapName, @NotNull String key, @Nullable String value) {
    this.set(value, Column.Game.MAP_PROPERTY_VALUE, new Entry<>(mapName, Column.Game.MAP_NAME),
        new Entry<>(key, Column.Game.MAP_PROPERTY_KEY));
  }

  public @NotNull Map<String, String> getProperties(@NotNull String mapName) {
    Map<String, String> map = new HashMap<>();
    for (ColumnMap m : this.get(Set.of(Column.Game.MAP_PROPERTY_KEY, Column.Game.MAP_PROPERTY_VALUE),
        new Entry<>(mapName, Column.Game.MAP_NAME))) {
      map.put(m.get(Column.Game.MAP_PROPERTY_KEY), m.get(Column.Game.MAP_PROPERTY_VALUE));
    }
    return map;
  }
}
