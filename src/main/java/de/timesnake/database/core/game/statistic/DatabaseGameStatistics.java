package de.timesnake.database.core.game.statistic;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.NotNull;

public class DatabaseGameStatistics extends DatabaseConnector {

    private final String userStatisticsTableName;
    private final String statisticTypesTableName;

    public DatabaseGameStatistics(String name, String url, String user, String password,
                                  String userStatisticsTableName, String statisticTypesTableName) {
        super(name, url, user, password);

        this.userStatisticsTableName = userStatisticsTableName;
        this.statisticTypesTableName = statisticTypesTableName;
    }

    @NotNull
    public StatisticsTable getGameUserStatistics(String gameName) {
        return new StatisticsTable(this, gameName + "_" + this.statisticTypesTableName,
                gameName + "_" + this.userStatisticsTableName);
    }

}
