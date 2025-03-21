/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.lounge;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.KeyedQueryTool;
import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class DbLoungeMapDisplay extends KeyedQueryTool implements
    de.timesnake.database.util.game.DbLoungeMapDisplay {

  protected DbLoungeMapDisplay(DatabaseConnector databaseConnector, String nameTable,
      String mapName,
      Integer displayIndex) {
    super(databaseConnector, nameTable, true, new Entry<>(mapName, Column.Game.LOUNGE_MAP_NAME),
        new Entry<>(displayIndex, Column.Game.LOUNGE_MAP_DISPLAY_INDEX));
  }

  @Override
  public boolean exists() {
    return this.getX() != null;
  }

  @NotNull
  @Override
  public String getMapName() {
    return super.keyEntries.get(Column.Game.LOUNGE_MAP_NAME).getValue();
  }

  @NotNull
  @Override
  public Integer getIndex() {
    return super.keyEntries.get(Column.Game.LOUNGE_MAP_DISPLAY_INDEX).getValue();
  }

  @NotNull
  @Override
  public Integer getX() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_X);
  }

  @Override
  public void setX(Integer x) {
    super.setWithKey(x, Column.Game.LOUNGE_MAP_DISPLAY_X);
  }

  @NotNull
  @Override
  public Integer getY() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_Y);
  }

  @Override
  public void setY(Integer y) {
    super.setWithKey(y, Column.Game.LOUNGE_MAP_DISPLAY_Y);
  }

  @NotNull
  @Override
  public Integer getZ() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_Z);
  }

  @Override
  public void setZ(Integer z) {
    super.setWithKey(z, Column.Game.LOUNGE_MAP_DISPLAY_Z);
  }

  @NotNull
  @Override
  public BlockSide getFacing() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_FACING);
  }

  @Override
  public void setFacing(BlockSide facing) {
    super.setWithKey(facing, Column.Game.LOUNGE_MAP_DISPLAY_FACING);
  }

  @NotNull
  @Override
  public BlockSide getOrientation() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_ORIENTATION);
  }

  @Override
  public void setOrientation(BlockSide orientation) {
    super.setWithKey(orientation, Column.Game.LOUNGE_MAP_DISPLAY_ORIENTATION);
  }

  @NotNull
  @Override
  public Color getTitleColor() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_TITLE_COLOR);
  }

  @Override
  public void setTitleColor(Color color) {
    super.setWithKey(color, Column.Game.LOUNGE_MAP_DISPLAY_TITLE_COLOR);
  }

  @NotNull
  @Override
  public Color getStatNameColor() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR);
  }

  @Override
  public void setStatNameColor(Color color) {
    super.setWithKey(color, Column.Game.LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR);
  }

  @NotNull
  @Override
  public Color getStatFirstColor() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR);
  }

  @Override
  public void setStatFirstColor(Color color) {
    super.setWithKey(color, Column.Game.LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR);
  }

  @NotNull
  @Override
  public Color getStatSecondColor() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR);
  }

  @Override
  public void setStatSecondColor(Color color) {
    super.setWithKey(color, Column.Game.LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR);
  }

  @NotNull
  @Override
  public Color getStatThirdColor() {
    return super.getFirstWithKey(Column.Game.LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR);
  }

  @Override
  public void setStatThirdColor(Color color) {
    super.setWithKey(color, Column.Game.LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR);
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbLoungeMapDisplay toLocal() {
    return this;
  }

  @NotNull
  @Override
  public de.timesnake.database.util.game.DbLoungeMapDisplay toDatabase() {
    return new DbCachedLoungeMapDisplay(this);
  }

}
