/*
 * database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.core.game.statistic;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class StatisticTypesTable extends TableDDL {

    protected StatisticTypesTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.STAT_TYPE_NAME);
        super.addColumn(Column.Game.STAT_TYPE_DISPLAY_NAME);
        super.addColumn(Column.Game.STAT_TYPE_TYPE);
        super.addColumn(Column.Game.STAT_TYPE_DEFAULT_VALUE);
        super.addColumn(Column.Game.STAT_TYPE_DISPLAY_INDEX);
        super.addColumn(Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX);
        super.addColumn(Column.Game.STAT_TYPE_GLOBAL_DISPLAY);
        super.addColumn(Column.Game.STAT_TYPE_GLOBAL_DISPLAY_INDEX);
        super.addColumn(Column.Game.STAT_TYPE_GLOBAL_DISPLAY_LINE_INDEX);
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

    public Set<StatType<?>> getStats() {
        Set<StatType<?>> stats = new HashSet<>();

        Set<ColumnMap> types = super.get(Set.of(Column.Game.STAT_TYPE_NAME, Column.Game.STAT_TYPE_TYPE,
                Column.Game.STAT_TYPE_DISPLAY_NAME, Column.Game.STAT_TYPE_DEFAULT_VALUE,
                Column.Game.STAT_TYPE_DISPLAY_INDEX, Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX,
                Column.Game.STAT_TYPE_GLOBAL_DISPLAY, Column.Game.STAT_TYPE_GLOBAL_DISPLAY_INDEX,
                Column.Game.STAT_TYPE_GLOBAL_DISPLAY_LINE_INDEX));

        for (ColumnMap map : types) {
            String typeString = map.get(Column.Game.STAT_TYPE_TYPE);

            String name = map.get(Column.Game.STAT_TYPE_NAME);
            String displayName = map.get(Column.Game.STAT_TYPE_DISPLAY_NAME);
            String defaultValue = map.get(Column.Game.STAT_TYPE_DEFAULT_VALUE);
            Integer displayIndex = map.get(Column.Game.STAT_TYPE_DISPLAY_INDEX);
            Integer displayLineIndex = map.get(Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX);
            Boolean globalDisplay = map.get(Column.Game.STAT_TYPE_GLOBAL_DISPLAY);
            Integer globalDisplayIndex = map.get(Column.Game.STAT_TYPE_GLOBAL_DISPLAY_INDEX);
            Integer globalDisplayLineIndex = map.get(Column.Game.STAT_TYPE_GLOBAL_DISPLAY_LINE_INDEX);

            stats.add(StatType.wrapStatType(typeString, name, displayName, defaultValue, displayIndex, displayLineIndex,
                    globalDisplay, globalDisplayIndex, globalDisplayLineIndex));
        }

        return stats;
    }

    @NotNull
    public StatType<?> getStat(String name) {
        ColumnMap map = super.getFirst(Set.of(Column.Game.STAT_TYPE_NAME, Column.Game.STAT_TYPE_TYPE,
                        Column.Game.STAT_TYPE_DISPLAY_NAME, Column.Game.STAT_TYPE_DEFAULT_VALUE,
                        Column.Game.STAT_TYPE_DISPLAY_INDEX, Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX),
                new TableEntry<>(name, Column.Game.STAT_TYPE_NAME));

        String typeString = map.get(Column.Game.STAT_TYPE_TYPE);

        String displayName = map.get(Column.Game.STAT_TYPE_DISPLAY_NAME);
        String defaultValue = map.get(Column.Game.STAT_TYPE_DEFAULT_VALUE);
        Integer displayIndex = map.get(Column.Game.STAT_TYPE_DISPLAY_INDEX);
        Integer displayLineIndex = map.get(Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX);
        Boolean globalDisplay = map.get(Column.Game.STAT_TYPE_GLOBAL_DISPLAY);
        Integer globalDisplayIndex = map.get(Column.Game.STAT_TYPE_GLOBAL_DISPLAY_INDEX);
        Integer globalDisplayLineIndex = map.get(Column.Game.STAT_TYPE_GLOBAL_DISPLAY_LINE_INDEX);

        return StatType.wrapStatType(typeString, name, displayName, defaultValue, displayIndex, displayLineIndex,
                globalDisplay, globalDisplayIndex, globalDisplayLineIndex);
    }

    public void addStat(StatType<?> stat) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(stat.getName(), Column.Game.STAT_TYPE_NAME)),
                new TableEntry<>(stat.getDisplayName(), Column.Game.STAT_TYPE_DISPLAY_NAME),
                new TableEntry<>(stat.getType(), Column.Game.STAT_TYPE_TYPE),
                new TableEntry<>(stat.getDefaultValueAsString(), Column.Game.STAT_TYPE_DEFAULT_VALUE),
                new TableEntry<>(stat.getDisplayIndex(), Column.Game.STAT_TYPE_DISPLAY_INDEX),
                new TableEntry<>(stat.getDisplayLineIndex(), Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX),
                new TableEntry<>(stat.getGlobalDisplay(), Column.Game.STAT_TYPE_GLOBAL_DISPLAY),
                new TableEntry<>(stat.getGlobalDisplayIndex(), Column.Game.STAT_TYPE_GLOBAL_DISPLAY_INDEX),
                new TableEntry<>(stat.getGlobalDisplayLineIndex(), Column.Game.STAT_TYPE_GLOBAL_DISPLAY_LINE_INDEX));
    }

    public void removeStat(StatType<?> stat) {
        super.deleteEntrySynchronized(new TableEntry<>(stat.getName(), Column.Game.STAT_TYPE_NAME));
    }

}
