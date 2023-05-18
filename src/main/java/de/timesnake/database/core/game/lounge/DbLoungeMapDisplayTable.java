/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.lounge;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DbLoungeMapDisplayTable extends TableDDL {

  public DbLoungeMapDisplayTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Column.Game.LOUNGE_MAP_NAME,
        Column.Game.LOUNGE_MAP_DISPLAY_INDEX);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_X);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_Y);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_Z);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_FACING);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_ORIENTATION);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_TITLE_COLOR);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR);
    super.addColumn(Column.Game.LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR);
  }

  public void create() {
    super.create();
  }

  @Override
  public void backup() {
    super.backup();
  }

  public void addDisplay(String mapName, Integer displayIndex, Integer x, Integer y, Integer z,
      BlockSide facing, BlockSide orientation, Color titleColor, Color statNameColor,
      Color firstColor, Color secondColor, Color thirdColor) {
    super.addEntry(new PrimaryEntries(new Entry<>(mapName, Column.Game.LOUNGE_MAP_NAME),
            new Entry<>(displayIndex, Column.Game.LOUNGE_MAP_DISPLAY_INDEX)),
        new Entry<>(x, Column.Game.LOUNGE_MAP_DISPLAY_X),
        new Entry<>(y, Column.Game.LOUNGE_MAP_DISPLAY_Y),
        new Entry<>(z, Column.Game.LOUNGE_MAP_DISPLAY_Z),
        new Entry<>(facing, Column.Game.LOUNGE_MAP_DISPLAY_FACING),
        new Entry<>(orientation, Column.Game.LOUNGE_MAP_DISPLAY_ORIENTATION),
        new Entry<>(titleColor, Column.Game.LOUNGE_MAP_DISPLAY_TITLE_COLOR),
        new Entry<>(statNameColor, Column.Game.LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR),
        new Entry<>(firstColor, Column.Game.LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR),
        new Entry<>(secondColor, Column.Game.LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR),
        new Entry<>(thirdColor, Column.Game.LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR));
  }

  public void removeDisplay(String mapName, Integer displayIndex) {
    super.deleteEntry(new Entry<>(mapName, Column.Game.LOUNGE_MAP_NAME),
        new Entry<>(displayIndex, Column.Game.LOUNGE_MAP_DISPLAY_INDEX));
  }

  public boolean containsDisplay(String mapName, Integer displayIndex) {
    return this.getDisplay(mapName, displayIndex).exists();
  }

  @NotNull
  public DbLoungeMapDisplay getDisplay(String mapName, Integer displayIndex) {
    return new DbLoungeMapDisplay(this.databaseConnector, this.tableName, mapName, displayIndex);
  }

  public Collection<de.timesnake.database.util.game.DbLoungeMapDisplay> getDisplays(
      String mapName) {
    ArrayList<de.timesnake.database.util.game.DbLoungeMapDisplay> displays = new ArrayList<>();
    for (Integer displayIndex : super.get(Column.Game.LOUNGE_MAP_DISPLAY_INDEX, new Entry<>(mapName,
        Column.Game.LOUNGE_MAP_NAME))) {
      displays.add(this.getDisplay(mapName, displayIndex));
    }
    return displays;
  }

  public Collection<de.timesnake.database.util.game.DbLoungeMapDisplay> getCachedDisplays(
      String mapName) {
    ArrayList<de.timesnake.database.util.game.DbLoungeMapDisplay> displays = new ArrayList<>();
    Set<ColumnMap> entries = super.get(Set.of(Column.Game.LOUNGE_MAP_DISPLAY_INDEX,
            Column.Game.LOUNGE_MAP_DISPLAY_X,
            Column.Game.LOUNGE_MAP_DISPLAY_Y, Column.Game.LOUNGE_MAP_DISPLAY_Z,
            Column.Game.LOUNGE_MAP_DISPLAY_FACING, Column.Game.LOUNGE_MAP_DISPLAY_ORIENTATION,
            Column.Game.LOUNGE_MAP_DISPLAY_TITLE_COLOR,
            Column.Game.LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR,
            Column.Game.LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR,
            Column.Game.LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR,
            Column.Game.LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR
        ),
        new Entry<>(mapName, Column.Game.LOUNGE_MAP_NAME));

    for (ColumnMap entry : entries) {
      displays.add(new DbCachedLoungeMapDisplay(this.getDisplay(mapName,
          entry.get(Column.Game.LOUNGE_MAP_DISPLAY_INDEX)), entry));
    }
    return displays;
  }


}
