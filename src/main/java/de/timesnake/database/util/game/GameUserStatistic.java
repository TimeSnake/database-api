/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.game;

import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import java.util.Collection;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GameUserStatistic {

  @NotNull
  Map<StatType<?>, Object> get(StatPeriod period, StatType<?>... stats);

  @NotNull
  Map<StatType<?>, Map<StatPeriod, Object>> get(Collection<StatPeriod> periods,
      StatType<?>... stats);

  @Nullable <Value> Value getValue(StatPeriod period, StatType<Value> stat);

  @NotNull <Value> Map<StatPeriod, Value> getValues(Collection<StatPeriod> periods,
      StatType<Value> stat);

  <Value> void set(StatPeriod period, Map<StatType<Value>, Value> valuesByType);

  <Value> void setValue(StatPeriod period, StatType<Value> type, Value value);

  <Value> void setValues(Map<StatPeriod, Value> valuesByPeriod, StatType<Value> type);

  <Value> void setValues(StatType<Value> type, Value value);

  @Deprecated
  <Value> void addValue(StatPeriod period, StatType<Value> type, Value value);
}
