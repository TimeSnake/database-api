/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.lounge;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.game.DbLoungeMapDisplay;
import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.DbLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collection;

public class DbLoungeMap extends KeyedQueryTool implements de.timesnake.database.util.game.DbLoungeMap {

  private final DbLoungeMapDisplayTable displayTable;

  public DbLoungeMap(DatabaseConnector databaseConnector, String nameTable, String mapName,
      DbLoungeMapDisplayTable displayTable) {
    super(databaseConnector, nameTable, true, new Entry<>(mapName, Column.Game.LOUNGE_MAP_NAME));
    this.displayTable = displayTable;
  }

  @Override
  public boolean exists() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_NAME) != null;
  }

  @NotNull
  @Override
  public String getName() {
    return super.keyEntries.get(Column.Game.LOUNGE_MAP_NAME).getValue();
  }

  @NotNull
  @Override
  public String getWorldName() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_WORLD);
  }

  @NotNull
  @Override
  public Float getX() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_X);
  }

  @NotNull
  @Override
  public Float getY() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_Y);
  }

  @NotNull
  @Override
  public Float getZ() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_Z);
  }

  @NotNull
  @Override
  public Float getYaw() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_YAW);
  }

  @NotNull
  @Override
  public Float getPitch() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_LOC_PITCH);
  }

  @NotNull
  @Override
  public DbLocation getLocation() {
    return new DbLocation(this.getWorldName(), this.getX(), this.getY(), this.getZ(), this.getYaw(),
        this.getPitch());
  }


  @Override
  public void addMapDisplay(Integer displayIndex, Integer x, Integer y, Integer z,
      BlockSide facing, BlockSide orientation, Color titleColor, Color statNameColor,
      Color firstColor, Color secondColor, Color thirdColor) {
    this.displayTable.addDisplay(this.getName(), displayIndex, x, y, z, facing, orientation,
        titleColor,
        statNameColor,
        firstColor, secondColor, thirdColor);
  }

  @Override
  public void removeMapDisplay(Integer displayIndex) {
    this.displayTable.removeDisplay(this.getName(), displayIndex);
  }

  @Override
  public boolean containsMapDisplay(Integer displayIndex) {
    return this.displayTable.containsDisplay(this.getName(), displayIndex);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbLoungeMapDisplay getMapDisplay(Integer displayIndex) {
    return this.displayTable.getDisplay(this.getName(), displayIndex);
  }

  @NotNull
  @Override
  public Collection<DbLoungeMapDisplay> getMapDisplays() {
    return this.displayTable.getDisplays(this.getName());
  }

  @NotNull
  @Override
  public Collection<DbLoungeMapDisplay> getCachedMapDisplays() {
    return this.displayTable.getCachedDisplays(this.getName());
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbLoungeMap toLocal() {
    return new DbCachedLoungeMap(this);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbLoungeMap toDatabase() {
    return this;
  }
}
