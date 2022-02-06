package de.timesnake.database.core.game.statistic;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.table.Table;
import de.timesnake.database.util.object.DatabaseConnector;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserStatisticsTable extends Table {
    protected UserStatisticsTable(DatabaseConnector databaseConnector, String tableName) {
        super(databaseConnector, tableName, Column.Game.STAT_USER_UUID, Column.Game.STAT_USER_TYPE);
        super.addColumn(Column.Game.STAT_USER_VALUE);
    }

    @Override
    public void create() {
        super.create();
    }

    public void backup() {
        super.createBackup();
    }

    @Override
    public void delete() {
        super.delete();
    }

    public GameUserStatistic getStatistic(UUID uuid) {
        return new GameUserStatistic(this.databaseConnector, this.tableName, uuid);
    }

    public Collection<GameUserStatistic> getStatistics() {
        return super.get(Column.Game.STAT_USER_UUID).stream().map(this::getStatistic).collect(Collectors.toList());
    }
}
