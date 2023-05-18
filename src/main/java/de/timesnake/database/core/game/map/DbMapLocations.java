/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.map;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;
import java.util.HashMap;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DbMapLocations extends TableQuery {

  protected DbMapLocations(DatabaseConnector databaseConnector, String gameName, String mapName) {
    super(databaseConnector, gameName, new Entry<>(mapName, Column.Game.MAP_NAME));
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Game.MAP_NAME) != null;
  }

  @NotNull
  public String getName() {
    return (String) super.primaryEntries.get(0).getValue();
  }

  @NotNull
  public String getWorldName() {
    return super.getFirstWithKey(Column.Location.WORLD);
  }

  @Nullable
  public DbLocation getLocation(Integer number) {
    if (number == null) {
      return null;
    }
    if (!this.containsLocation(number)) {
      return null;
    }

    ColumnMap columnMap = super.getFirstWithKey(Set.of(Column.Location.WORLD, Column.Location.X,
            Column.Location.Y, Column.Location.Z, Column.Location.YAW, Column.Location.PITCH),
        new Entry<>(number, Column.Location.NUMBER));

    return new DbLocation(columnMap.get(Column.Location.WORLD), columnMap.get(Column.Location.X),
        columnMap.get(Column.Location.Y), columnMap.get(Column.Location.Z),
        columnMap.get(Column.Location.YAW), columnMap.get(Column.Location.PITCH));
  }

  @Nullable
  public DbLocation getFirstLocation() {
    return this.getLocation(this.getFirstLocationNumber());
  }

  @Nullable
  public Integer getFirstLocationNumber() {
    return super.getLowestIntegerWithKey(Column.Location.NUMBER);
  }

  @Nullable
  public DbLocation getLastLocation() {
    return this.getLocation(this.getLastLocationNumber());
  }

  @Nullable
  public Integer getLastLocationNumber() {
    return super.getHighestIntegerWithKey(Column.Location.NUMBER);
  }

  public HashMap<Integer, DbLocation> getLocations() {
    HashMap<Integer, DbLocation> spawns = new HashMap<>();
    for (Integer number : super.getWithKey(Column.Location.NUMBER)) {
      spawns.put(number, this.getLocation(number));
    }
    return spawns;
  }

  public void addLocation(Integer number, DbLocation location) {
    if (number == null) {
      return;
    }
    super.addEntry(super.primaryEntries.with(new Entry<>(number, Column.Location.NUMBER)),
        new Entry<>(location.getWorldName(), Column.Location.WORLD),
        new Entry<>(((float) location.getX()), Column.Location.X),
        new Entry<>(((float) location.getY()), Column.Location.Y),
        new Entry<>(((float) location.getZ()), Column.Location.Z),
        new Entry<>(location.getYaw(), Column.Location.YAW),
        new Entry<>(location.getPitch(), Column.Location.PITCH));
  }

  public void deleteLocation(Integer number) {
    if (number == null) {
      return;
    }
    super.deleteWithKey(new Entry<>(number, Column.Location.NUMBER));
  }

  public boolean containsLocation(Integer number) {
    return
        super.getFirstWithKey(Column.Location.NUMBER, new Entry<>(number, Column.Location.NUMBER))
            != null;
  }


}