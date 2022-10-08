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

package de.timesnake.database.util.game;

import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

public interface GameUserStatistic {

    @NotNull
    Map<StatType<?>, Object> get(StatPeriod period, StatType<?>... stats);

    @NotNull
    Map<StatType<?>, Map<StatPeriod, Object>> get(Collection<StatPeriod> periods, StatType<?>... stats);

    @Nullable <Value> Value getValue(StatPeriod period, StatType<Value> stat);

    @NotNull <Value> Map<StatPeriod, Value> getValues(Collection<StatPeriod> periods, StatType<Value> stat);

    <Value> void set(StatPeriod period, Map<StatType<Value>, Value> valuesByType);

    <Value> void setValue(StatPeriod period, StatType<Value> type, Value value);

    <Value> void setValues(Map<StatPeriod, Value> valuesByPeriod, StatType<Value> type);

    <Value> void setValues(StatType<Value> type, Value value);

    @Deprecated
    <Value> void addValue(StatPeriod period, StatType<Value> type, Value value);
}
