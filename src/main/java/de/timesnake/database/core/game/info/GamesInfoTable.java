package de.timesnake.database.core.game.info;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public class GamesInfoTable extends GamesInfoBasisTable {

    protected GamesInfoTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Game.MAX_PLAYERS);
        super.addColumn(Column.Game.KITS);
        super.addColumn(Column.Game.MAPS);
        super.addColumn(Column.Game.STATISTICS);
        super.addColumn(Column.Game.TEXTURE_PACK_LINK);
        super.addColumn(Column.Game.PLAYER_TRACKING_RANGE);
        super.addColumn(Column.Game.MAX_HEALTH);
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
    public DbGameInfo getGame(String name) {
        return new DbGameInfo(this.databaseConnector, this.tableName, name);
    }
}
