package de.timesnake.database.util.game;

import de.timesnake.library.basic.util.statistics.Stat;

import java.util.Map;

public interface GameUserStatistic {
    Map<Stat<?>, Object> get(Stat<?>... stats);

    <Value> Value getValue(Stat<Value> stat);

    <Value> void set(Map<Stat<Value>, Value> valuesByType);

    <Value> void setValue(Stat<Value> type, Value value);

    <Value> void addValue(Stat<Value> type, Value value);
}
