package de.timesnake.database.core.game.statistic;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.Stat;

import java.util.HashSet;
import java.util.Set;

public class StatisticTypesTable extends Table {

    protected StatisticTypesTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.STAT_TYPE_NAME);
        super.addColumn(Column.Game.STAT_TYPE_DISPLAY_NAME);
        super.addColumn(Column.Game.STAT_TYPE_TYPE);
        super.addColumn(Column.Game.STAT_TYPE_DEFAULT_VALUE);
        super.addColumn(Column.Game.STAT_TYPE_DISPLAY_INDEX);
        super.addColumn(Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX);
    }

    @Override
    public void create() {
        super.create();
    }

    public void backup() {
        super.createBackup();
    }

    @Override
    public void delete() {
        super.delete();
    }

    public Set<Stat<?>> getStats() {
        Set<Stat<?>> stats = new HashSet<>();

        Set<ColumnMap> types = super.get(Set.of(Column.Game.STAT_TYPE_NAME, Column.Game.STAT_TYPE_TYPE, Column.Game.STAT_TYPE_DISPLAY_NAME, Column.Game.STAT_TYPE_DEFAULT_VALUE, Column.Game.STAT_TYPE_DISPLAY_INDEX, Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX));

        for (ColumnMap map : types) {
            String typeString = map.get(Column.Game.STAT_TYPE_TYPE);

            Stat.Type<?> type = Stat.Type.TYPES_BY_NAME.get(typeString);

            if (type == null) {
                continue;
            }

            String name = map.get(Column.Game.STAT_TYPE_NAME);
            String displayName = map.get(Column.Game.STAT_TYPE_DISPLAY_NAME);
            Object defaultValue = type.valueOf(map.get(Column.Game.STAT_TYPE_DEFAULT_VALUE));
            Integer displayIndex = map.get(Column.Game.STAT_TYPE_DISPLAY_INDEX);
            Integer displayLineIndex = map.get(Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX);

            stats.add(type.asStat(name, displayName, defaultValue, displayIndex, displayLineIndex));
        }

        return stats;
    }

    public <ValueType> Stat<ValueType> getStat(String name, Stat.Type<ValueType> type) {
        ColumnMap map = super.getFirst(Set.of(Column.Game.STAT_TYPE_NAME, Column.Game.STAT_TYPE_TYPE, Column.Game.STAT_TYPE_DISPLAY_NAME, Column.Game.STAT_TYPE_DEFAULT_VALUE, Column.Game.STAT_TYPE_DISPLAY_INDEX, Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX), new TableEntry<>(name, Column.Game.STAT_TYPE_NAME));

        String displayName = map.get(Column.Game.STAT_TYPE_DISPLAY_NAME);
        Object defaultValue = type.valueOf(map.get(Column.Game.STAT_TYPE_DEFAULT_VALUE));
        Integer displayIndex = map.get(Column.Game.STAT_TYPE_DISPLAY_INDEX);
        Integer displayLineIndex = map.get(Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX);

        return type.asStat(name, displayName, defaultValue, displayIndex, displayLineIndex);
    }

    public void addStat(Stat<?> stat) {
        super.addEntry(new PrimaryEntries(new TableEntry<>(stat.getName(), Column.Game.STAT_TYPE_NAME)), new TableEntry<>(stat.getDisplayName(), Column.Game.STAT_TYPE_DISPLAY_NAME), new TableEntry<>(stat.getType().getName(), Column.Game.STAT_TYPE_TYPE), new TableEntry<>(stat.getDefaultValueAsString(), Column.Game.STAT_TYPE_DEFAULT_VALUE), new TableEntry<>(stat.getDisplayIndex(), Column.Game.STAT_TYPE_DISPLAY_INDEX), new TableEntry<>(stat.getDisplayLineIndex(), Column.Game.STAT_TYPE_DISPLAY_LINE_INDEX));
    }

    public void removeStat(Stat<?> stat) {
        super.deleteEntrySynchronized(new TableEntry<>(stat.getName(), Column.Game.STAT_TYPE_NAME));
    }

}
