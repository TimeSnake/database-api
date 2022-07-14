package de.timesnake.database.core.game.lounge;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.BlockSide;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class DbLoungeMapDisplayTable extends TableDDL {

    public DbLoungeMapDisplayTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.LOUNGE_MAP_NAME, Column.Game.LOUNGE_MAP_DISPLAY_INDEX);
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
        super.addEntry(new PrimaryEntries(new TableEntry<>(mapName, Column.Game.LOUNGE_MAP_NAME),
                        new TableEntry<>(displayIndex, Column.Game.LOUNGE_MAP_DISPLAY_INDEX)),
                new TableEntry<>(x, Column.Game.LOUNGE_MAP_DISPLAY_X),
                new TableEntry<>(y, Column.Game.LOUNGE_MAP_DISPLAY_Y),
                new TableEntry<>(z, Column.Game.LOUNGE_MAP_DISPLAY_Z),
                new TableEntry<>(facing, Column.Game.LOUNGE_MAP_DISPLAY_FACING),
                new TableEntry<>(orientation, Column.Game.LOUNGE_MAP_DISPLAY_ORIENTATION),
                new TableEntry<>(titleColor, Column.Game.LOUNGE_MAP_DISPLAY_TITLE_COLOR),
                new TableEntry<>(statNameColor, Column.Game.LOUNGE_MAP_DISPLAY_STAT_NAME_COLOR),
                new TableEntry<>(firstColor, Column.Game.LOUNGE_MAP_DISPLAY_STAT_FIRST_COLOR),
                new TableEntry<>(secondColor, Column.Game.LOUNGE_MAP_DISPLAY_STAT_SECOND_COLOR),
                new TableEntry<>(thirdColor, Column.Game.LOUNGE_MAP_DISPLAY_STAT_THIRD_COLOR));
    }

    public void removeDisplay(String mapName, Integer displayIndex) {
        super.deleteEntry(new TableEntry<>(mapName, Column.Game.LOUNGE_MAP_NAME),
                new TableEntry<>(displayIndex, Column.Game.LOUNGE_MAP_DISPLAY_INDEX));
    }

    public boolean containsDisplay(String mapName, Integer displayIndex) {
        return this.getDisplay(mapName, displayIndex).exists();
    }

    public DbLoungeMapDisplay getDisplay(String mapName, Integer displayIndex) {
        return new DbLoungeMapDisplay(this.databaseConnector, this.tableName, mapName, displayIndex);
    }

    public Collection<de.timesnake.database.util.game.DbLoungeMapDisplay> getDisplays(String mapName) {
        ArrayList<de.timesnake.database.util.game.DbLoungeMapDisplay> displays = new ArrayList<>();
        for (Integer displayIndex : super.get(Column.Game.LOUNGE_MAP_DISPLAY_INDEX, new TableEntry<>(mapName,
                Column.Game.LOUNGE_MAP_NAME))) {
            displays.add(this.getDisplay(mapName, displayIndex));
        }
        return displays;
    }

    public Collection<de.timesnake.database.util.game.DbLoungeMapDisplay> getCachedDisplays(String mapName) {
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
                new TableEntry<>(mapName, Column.Game.LOUNGE_MAP_NAME));

        for (ColumnMap entry : entries) {
            displays.add(new DbCachedLoungeMapDisplay(this.getDisplay(mapName,
                    entry.get(Column.Game.LOUNGE_MAP_DISPLAY_INDEX)), entry));
        }
        return displays;
    }


}
