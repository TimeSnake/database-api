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
import org.jetbrains.annotations.NotNull;

public class DatabaseGameStatistics extends DatabaseConnector {

    private final String userStatisticsTableName;
    private final String statisticTypesTableName;

    public DatabaseGameStatistics(String name, String url, String options, String user, String password,
                                  String userStatisticsTableName, String statisticTypesTableName) {
        super(name, url, options, user, password);

        this.userStatisticsTableName = userStatisticsTableName;
        this.statisticTypesTableName = statisticTypesTableName;
    }

    @NotNull
    public StatisticsTable getGameUserStatistics(String gameName) {
        return new StatisticsTable(this, gameName + "_" + this.statisticTypesTableName,
                gameName + "_" + this.userStatisticsTableName);
    }

}
