/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.game.statistic;

import de.timesnake.channel.core.Channel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        super(databaseConnector, nameTable, new Entry<>(uuid, Column.Game.STAT_USER_UUID));

        super.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
    }

    @Override
    public boolean exists() {
        return true;
    }

    @NotNull
    @Override
    public Map<StatType<?>, Object> get(StatPeriod period, StatType<?>... stats) {
        return this.get(List.of(period), stats).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                entry -> entry.getValue().get(period), (a, b) -> b));
    }

    @NotNull
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

    @Nullable
    @Override
    public <Value> Value getValue(StatPeriod period, StatType<Value> stat) {
        return this.getValues(List.of(period), stat).get(period);
    }

    @NotNull
    @Override
    public <Value> Map<StatPeriod, Value> getValues(Collection<StatPeriod> periods, StatType<Value> stat) {
        ColumnMap map = super.getFirstWithKey(getPeriodColumns(periods),
                new Entry<>(stat.getName(), Column.Game.STAT_USER_TYPE));

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
        Set<Entry<?>> values = new HashSet<>();

        for (Map.Entry<StatPeriod, Value> entry : valuesByPeriod.entrySet()) {
            values.add(new Entry<>(type.valueToString(entry.getValue()),
                    getPeriodColumn(entry.getKey())));
        }

        super.setWithKey(values, () ->
                        Channel.getInstance().sendMessageSynchronized(new ChannelUserMessage<>(((UUID)
                                this.primaryEntries.get(0).getValue()), MessageType.User.STATISTICS, type.getName())),
                new Entry<>(type.getName(), Column.Game.STAT_USER_TYPE));

        // TODO safe set
    }

    @Override
    public <Value> void setValues(StatType<Value> type, Value value) {
        Set<Entry<?>> values = new HashSet<>();

        for (StatPeriod period : StatPeriod.values()) {
            values.add(new Entry<>(type.valueToString(value),
                    getPeriodColumn(period)));
        }

        super.setWithKey(values, () ->
                        Channel.getInstance().sendMessageSynchronized(new ChannelUserMessage<>(((UUID)
                                this.primaryEntries.get(0).getValue()), MessageType.User.STATISTICS, type.getName())),
                new Entry<>(type.getName(), Column.Game.STAT_USER_TYPE));
    }

    @Override
    @Deprecated
    public <Value> void addValue(StatPeriod period, StatType<Value> type, Value value) {
        super.addEntry(this.primaryEntries.with(new Entry<>(type.getName(), Column.Game.STAT_USER_TYPE)),
                () -> Channel.getInstance().sendMessageSynchronized(new ChannelUserMessage<>(((UUID)
                        this.primaryEntries.get(0).getValue()), MessageType.User.STATISTICS, type.getName())),
                new Entry<>(type.valueToString(value), getPeriodColumn(period)));
    }

}
