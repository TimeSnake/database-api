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
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.core.table.TableDDL;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;

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

    @NotNull
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
