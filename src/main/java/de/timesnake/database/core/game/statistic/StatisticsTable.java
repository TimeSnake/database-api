/*
 * workspace.database-api.main
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

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.statistics.StatPeriod;
import de.timesnake.library.basic.util.statistics.StatType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class StatisticsTable {

    private final StatisticTypesTable typesTable;
    private final UserStatisticsTable userTable;

    protected StatisticsTable(DatabaseConnector databaseConnector, String typesTableName, String userTableName) {
        this.typesTable = new StatisticTypesTable(databaseConnector, typesTableName);
        this.userTable = new UserStatisticsTable(databaseConnector, userTableName);
    }

    public void create() {
        this.typesTable.create();
        this.userTable.create();
    }

    public void backup() {
        this.typesTable.backup();
        this.userTable.backup();
    }

    public void delete() {
        this.typesTable.delete();
        this.userTable.delete();
    }

    public Set<StatType<?>> getStats() {
        return typesTable.getStats();
    }

    @NotNull
    public StatType<?> getStat(String name) {
        return typesTable.getStat(name);
    }

    public void addStat(StatType<?> stat) {
        typesTable.addStat(stat);
    }

    public void removeStat(StatType<?> stat) {
        typesTable.removeStat(stat);
    }

    public List<GameUserStatistic> getUserStatistics() {
        return this.userTable.getStatistics();
    }

    @NotNull
    public GameUserStatistic getUserStatistic(UUID uuid) {
        return this.userTable.getStatistic(uuid);
    }

    public <Value> Map<UUID, Value> getStatOfUsers(StatPeriod period, StatType<Value> type) {
        return this.userTable.getStatOfUsers(period, type);
    }
}
