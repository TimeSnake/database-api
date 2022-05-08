package de.timesnake.database.core.game.statistic;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.library.basic.util.Tuple;
import de.timesnake.library.basic.util.statistics.Stat;

import java.util.Collection;
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

    public Set<Stat<?>> getStats() {
        return typesTable.getStats();
    }

    public <ValueType> Stat<ValueType> getStat(String name, Stat.Type<ValueType> type) {
        return typesTable.getStat(name, type);
    }

    public void addStat(Stat<?> stat) {
        typesTable.addStat(stat);
    }

    public void removeStat(Stat<?> stat) {
        typesTable.removeStat(stat);
    }

    public Collection<GameUserStatistic> getUserStatistics() {
        return this.userTable.getStatistics();
    }

    public GameUserStatistic getUserStatistic(UUID uuid) {
        return this.userTable.getStatistic(uuid);
    }

    public <Value> Collection<Tuple<UUID, Value>> getStatOfUsers(Stat<Value> type) {
        return this.userTable.getStatOfUsers(type);
    }
}
