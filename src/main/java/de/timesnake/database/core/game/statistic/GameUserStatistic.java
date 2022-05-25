package de.timesnake.database.core.game.statistic;

import de.timesnake.channel.core.NetworkChannel;
import de.timesnake.channel.util.message.ChannelUserMessage;
import de.timesnake.channel.util.message.MessageType;
import de.timesnake.database.core.Column;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableQuery;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.Stat;

import java.util.*;

public class GameUserStatistic extends TableQuery implements de.timesnake.database.util.game.GameUserStatistic {

    protected GameUserStatistic(DatabaseConnector databaseConnector, String nameTable, UUID uuid) {
        super(databaseConnector, nameTable, new TableEntry<>(uuid, Column.Game.STAT_USER_UUID));
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public Map<Stat<?>, Object> get(Stat<?>... stats) {
        Map<Stat<?>, Object> valuesByType = new HashMap<>();

        List<Stat<?>> statList = Arrays.asList(stats);

        for (ColumnMap columnMap : super.getWithKey(Set.of(Column.Game.STAT_USER_TYPE, Column.Game.STAT_USER_VALUE))) {
            String name = columnMap.get(Column.Game.STAT_USER_TYPE);

            Stat<?> stat = null;
            for (Stat<?> currentStat : statList) {
                if (currentStat.getName().equals(name)) {
                    stat = currentStat;
                    break;
                }
            }

            if (stat == null) {
                continue;
            }

            statList.remove(stat);

            String value = columnMap.get(Column.Game.STAT_USER_VALUE);

            valuesByType.put(stat, value != null ? stat.getType().valueOf(value) : stat.getDefaultValue());
        }
        return valuesByType;
    }

    @Override
    public <Value> Value getValue(Stat<Value> stat) {
        return stat.getType().valueOf(super.getFirstWithKey(Column.Game.STAT_USER_VALUE,
                new TableEntry<>(stat.getName(), Column.Game.STAT_USER_TYPE)));
    }

    @Override
    public <Value> void set(Map<Stat<Value>, Value> valuesByType) {
        for (Map.Entry<Stat<Value>, Value> entry : valuesByType.entrySet()) {
            this.setValue(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public <Value> void setValue(Stat<Value> type, Value value) {
        super.setWithKey(type.getType().valueToString(value), Column.Game.STAT_USER_VALUE,
                () -> NetworkChannel.getChannel().sendMessageSynchronized(new ChannelUserMessage<>(((UUID) this.primaryEntries.get(0).getValue()), MessageType.User.STATISTICS, type.getName())), new TableEntry<>(type.getName(), Column.Game.STAT_USER_TYPE));
    }

    @Override
    public <Value> void addValue(Stat<Value> type, Value value) {
        super.addEntry(this.primaryEntries.with(new TableEntry<>(type.getName(), Column.Game.STAT_USER_TYPE)),
                () -> NetworkChannel.getChannel().sendMessageSynchronized(new ChannelUserMessage<>(((UUID) this.primaryEntries.get(0).getValue()), MessageType.User.STATISTICS, type.getName())), new TableEntry<>(type.getType().valueToString(value), Column.Game.STAT_USER_VALUE));
    }

}
