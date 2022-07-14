package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public class NonTmpGamesInfoTable extends GamesInfoTable {

    public NonTmpGamesInfoTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Game.GENERATEABLE);
        super.addColumn(Column.Game.ALLOW_AUTO_DELETE);
        super.addColumn(Column.Game.OWNABLE);
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
    public DbNonTmpGameInfo getGame(String name) {
        return new DbNonTmpGameInfo(this.databaseConnector, this.tableName, name);
    }
}
