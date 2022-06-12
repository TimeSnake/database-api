package de.timesnake.database.core.game.statistic;

import de.timesnake.channel.core.NetworkChannel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;

import java.util.*;
import java.util.stream.Collectors;

public class GameUserStatistic extends TableQuery implements de.timesnake.database.util.game.GameUserStatistic {

    public static Column<String> getPeriodColumn(StatPeriod period) {
        return switch (period) {
            case ALL_TIME -> Column.Game.STAT_USER_VALUE_ALL_TIME;
            case QUARTER -> Column.Game.STAT_USER_VALUE_QUARTER;
        };
    }

    public static Set<Column<?>> getPeriodColumns(Collection<StatPeriod> periods) {
        HashSet<Column<?>> columns = new HashSet<>();
        for (StatPeriod period : periods) {
            columns.add(getPeriodColumn(period));
        }
        return columns;
    }

    protected GameUserStatistic(DatabaseConnector databaseConnector, String nameTable, UUID uuid) {
        super(databaseConnector, nameTable, new TableEntry<>(uuid, Column.Game.STAT_USER_UUID));

        super.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public Map<StatType<?>, Object> get(StatPeriod period, StatType<?>... stats) {
        return this.get(List.of(period), stats).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                entry -> entry.getValue().get(period), (a, b) -> b));
    }

    @Override
    public Map<StatType<?>, Map<StatPeriod, Object>> get(Collection<StatPeriod> periods, StatType<?>... stats) {
        Map<StatType<?>, Map<StatPeriod, Object>> valuesByType = new HashMap<>();

        List<StatType<?>> statList = new LinkedList<>(Arrays.asList(stats));

        Set<Column<?>> columns = new HashSet<>();

        for (StatType<?> stat : stats) {
            valuesByType.put(stat, new HashMap<>());
        }

        for (StatPeriod period : periods) {
            columns.add(getPeriodColumn(period));
        }
        columns.add(Column.Game.STAT_USER_TYPE);

        for (ColumnMap columnMap : super.getWithKey(columns)) {
            String name = columnMap.get(Column.Game.STAT_USER_TYPE);

            StatType<?> stat = null;
            for (StatType<?> currentStat : statList) {
                if (currentStat.getName().equals(name)) {
                    stat = currentStat;
                    for (StatPeriod period : periods) {
                        String value = columnMap.get(getPeriodColumn(period));
                        valuesByType.get(stat).put(period, value != null ? stat.valueOf(value) :
                                stat.getDefaultValue());
                    }
                    break;
                }
            }

            statList.remove(stat);
        }

        for (StatType<?> stat : statList) {
            for (StatPeriod period : periods) {
                valuesByType.get(stat).put(period, stat.getDefaultValue());
            }

        }

        return valuesByType;
    }

    @Override
    public <Value> Value getValue(StatPeriod period, StatType<Value> stat) {
        return this.getValues(List.of(period), stat).get(period);
    }

    @Override
    public <Value> Map<StatPeriod, Value> getValues(Collection<StatPeriod> periods, StatType<Value> stat) {
        ColumnMap map = super.getFirstWithKey(getPeriodColumns(periods),
                new TableEntry<>(stat.getName(), Column.Game.STAT_USER_TYPE));

        HashMap<StatPeriod, Value> result = new HashMap<>();

        for (StatPeriod period : periods) {
            result.put(period, stat.valueOf(map.get(getPeriodColumn(period))));
        }

        return result;
    }

    @Override
    public <Value> void set(StatPeriod period, Map<StatType<Value>, Value> valuesByType) {
        for (Map.Entry<StatType<Value>, Value> entry : valuesByType.entrySet()) {
            this.setValue(period, entry.getKey(), entry.getValue());
        }
    }

    @Override
    public <Value> void setValue(StatPeriod period, StatType<Value> type, Value value) {
        this.setValues(Map.of(period, value), type);
    }

    @Override
    public <Value> void setValues(Map<StatPeriod, Value> valuesByPeriod, StatType<Value> type) {
        Set<TableEntry<?>> values = new HashSet<>();

        for (Map.Entry<StatPeriod, Value> entry : valuesByPeriod.entrySet()) {
            values.add(new TableEntry<>(type.valueToString(entry.getValue()),
                    getPeriodColumn(entry.getKey())));
        }

        super.setWithKey(values, () ->
                        NetworkChannel.getChannel().sendMessageSynchronized(new ChannelUserMessage<>(((UUID)
                                this.primaryEntries.get(0).getValue()), MessageType.User.STATISTICS, type.getName())),
                new TableEntry<>(type.getName(), Column.Game.STAT_USER_TYPE));

        // TODO safe set
    }

    @Override
    public <Value> void setValues(StatType<Value> type, Value value) {
        Set<TableEntry<?>> values = new HashSet<>();

        for (StatPeriod period : StatPeriod.values()) {
            values.add(new TableEntry<>(type.valueToString(value),
                    getPeriodColumn(period)));
        }

        super.setWithKey(values, () ->
                        NetworkChannel.getChannel().sendMessageSynchronized(new ChannelUserMessage<>(((UUID)
                                this.primaryEntries.get(0).getValue()), MessageType.User.STATISTICS, type.getName())),
                new TableEntry<>(type.getName(), Column.Game.STAT_USER_TYPE));
    }

    @Override
    @Deprecated
    public <Value> void addValue(StatPeriod period, StatType<Value> type, Value value) {
        super.addEntry(this.primaryEntries.with(new TableEntry<>(type.getName(), Column.Game.STAT_USER_TYPE)),
                () -> NetworkChannel.getChannel().sendMessageSynchronized(new ChannelUserMessage<>(((UUID)
                        this.primaryEntries.get(0).getValue()), MessageType.User.STATISTICS, type.getName())),
                new TableEntry<>(type.valueToString(value), getPeriodColumn(period)));
    }

}
