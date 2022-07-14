package de.timesnake.database.core.game.statistic;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;

import java.util.*;
import java.util.stream.Collectors;

public class UserStatisticsTable extends TableDDL {
    protected UserStatisticsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.STAT_USER_UUID, Column.Game.STAT_USER_TYPE);
        super.addColumn(Column.Game.STAT_USER_VALUE_QUARTER);
        super.addColumn(Column.Game.STAT_USER_VALUE_ALL_TIME);

        super.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
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

    public GameUserStatistic getStatistic(UUID uuid) {
        return new GameUserStatistic(this.databaseConnector, this.tableName, uuid);
    }

    public Collection<GameUserStatistic> getStatistics() {
        return super.get(Column.Game.STAT_USER_UUID).stream().map(this::getStatistic).collect(Collectors.toList());
    }

    public <Value> Map<UUID, Value> getStatOfUsers(StatPeriod period, StatType<Value> stat) {
        HashMap<UUID, Value> result = new HashMap<>();
        Column<String> valueColumn = GameUserStatistic.getPeriodColumn(period);

        Set<ColumnMap> maps = super.get(Set.of(Column.Game.STAT_USER_UUID, valueColumn),
                new TableEntry<>(stat.getName(), Column.Game.STAT_USER_TYPE));


        for (ColumnMap map : maps) {
            UUID uuid = map.get(Column.Game.STAT_USER_UUID);
            Value value = stat.valueOf(map.get(valueColumn));

            result.put(uuid, value);
        }

        return result;
    }
}
