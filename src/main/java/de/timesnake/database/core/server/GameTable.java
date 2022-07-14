package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public class GameTable extends PvPTable<DbGameServer> {


    public GameTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Server.GAME_INFO);
    }

    @Override
    public void backup() {
        Column[] columns = {Column.Server.PORT, Column.Server.NAME, Column.Server.MAX_PLAYERS, Column.Server.TASK,
                Column.Server.PASSWORD, Column.Server.OLD_PVP};
        super.backup(columns);
    }

    @Override
    public DbGameServer getServer(int port) {
        DbGameServer server = new DbGameServer(this.databaseConnector, port, this.tableName);
        return server.exists() ? server : null;
    }
}
