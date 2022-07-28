package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public class TmpGamesInfoTable extends GamesInfoTable {

    public TmpGamesInfoTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Game.AUTO_START_PLAYER_NUMBER);
        super.addColumn(Column.Game.MIN_PLAYER_NUMBER);
        super.addColumn(Column.Game.TEAM_SIZES);
        super.addColumn(Column.Game.TEAM_MERGE);
        super.addColumn(Column.Game.EQUAL_TEAM_SIZE_REQUIRED);
        super.addColumn(Column.Game.HIDE_TEAMS);
        super.addColumn(Column.Game.DESCRIPTION);
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
    public DbTmpGameInfo getGame(String name) {
        return new DbTmpGameInfo(this.databaseConnector, this.tableName, name);
    }
}
