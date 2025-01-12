/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core;

import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocationTable extends DefinitionAndQueryTool {

  @SafeVarargs
  public LocationTable(DatabaseConnector databaseConnector, String tableName, Column<String>... primaryColumns) {
    super(databaseConnector, tableName, new ArrayList<>() {{
      addAll(List.of(primaryColumns));
      add(Column.Location.NUMBER);
    }});
    super.addColumn(Column.Location.WORLD);
    super.addColumn(Column.Location.X);
    super.addColumn(Column.Location.Y);
    super.addColumn(Column.Location.Z);
    super.addColumn(Column.Location.YAW);
    super.addColumn(Column.Location.PITCH);
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

  public boolean containsWorld(String worldName) {
    return super.get(Column.Location.WORLD).contains(worldName);
  }

  public void deleteWorld(String worldName) {
    super.deleteEntry(new Entry<>(worldName, Column.Location.WORLD));
  }

  public de.timesnake.database.util.object.DbLocation getLocation(Integer number,
      String worldName) {
    if (number == null || worldName == null) {
      return null;
    }
    Entry<Integer> numberEntry = new Entry<>(number, Column.Location.NUMBER);
    Entry<String> worldEntry = new Entry<>(worldName, Column.Location.WORLD);

    return new de.timesnake.database.util.object.DbLocation(worldName,
        super.getFirst(Column.Location.X,
            numberEntry, worldEntry), super.getFirst(Column.Location.Y, numberEntry, worldEntry),
        super.getFirst(Column.Location.Z, numberEntry, worldEntry),
        super.getFirst(Column.Location.YAW,
            numberEntry, worldEntry),
        super.getFirst(Column.Location.PITCH, numberEntry, worldEntry));
  }

  public de.timesnake.database.util.object.DbLocation getFirstLocation(String worldName) {
    return this.getLocation(this.getFirstLocationNumber(worldName), worldName);
  }

  public Integer getFirstLocationNumber(String worldName) {
    return super.getLowestInteger(Column.Location.NUMBER,
        new Entry<>(worldName, Column.Location.WORLD));
  }

  public de.timesnake.database.util.object.DbLocation getLastSpawn(String worldName) {
    return this.getLocation(this.getLastLocationNumber(worldName), worldName);
  }

  public Integer getLastLocationNumber(String worldName) {
    return super.getHighestInteger(Column.Location.NUMBER,
        new Entry<>(worldName, Column.Location.WORLD));
  }

  public HashMap<Integer, DbLocation> getLocations(String worldName) {
    HashMap<Integer, de.timesnake.database.util.object.DbLocation> spawns = new HashMap<>();
    for (Integer number : super.get(Column.Location.NUMBER,
        new Entry<>(worldName, Column.Location.WORLD))) {
      spawns.put(number, this.getLocation(number, worldName));
    }
    return spawns;
  }

  public void addLocation(Integer number, de.timesnake.database.util.object.DbLocation location,
      Entry<?> primaryEntries) {
    if (number == null) {
      return;
    }
    super.addEntry(new PrimaryKeyEntries(new Entry<>(number, Column.Location.NUMBER),
            new Entry<>(location.getWorldName(), Column.Location.WORLD), primaryEntries),
        new Entry<>(number, Column.Location.NUMBER),
        new Entry<>(location.getWorldName(), Column.Location.WORLD),
        new Entry<>(((float) location.getX()), Column.Location.X),
        new Entry<>(((float) location.getY()), Column.Location.Y),
        new Entry<>(((float) location.getZ()), Column.Location.Z),
        new Entry<>(location.getYaw(), Column.Location.YAW),
        new Entry<>(location.getPitch(), Column.Location.PITCH));
  }

  public void deleteSpawn(Integer number, String worldName) {
    if (number == null || worldName == null) {
      return;
    }
    super.deleteEntry(new Entry<>(number, Column.Location.NUMBER), new Entry<>(worldName,
        Column.Location.WORLD));
  }

  public boolean containsSpawn(Integer number, String worldName) {
    return this.getLocation(number, worldName).getWorldName() != null;
  }


}
