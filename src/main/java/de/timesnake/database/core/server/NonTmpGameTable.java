package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public class NonTmpGameTable extends PvPTable<DbNonTmpGameServer> {


    public NonTmpGameTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Server.GAME_INFO);
        super.addColumn(Column.Server.OWNER);
    }

    @Override
    public void backup() {
        Column<?>[] columns = {Column.Server.PORT, Column.Server.NAME, Column.Server.MAX_PLAYERS, Column.Server.TASK,
                Column.Server.PASSWORD, Column.Server.OLD_PVP, Column.Server.OWNER};
        super.backup(columns);
    }

    @Override
    public DbNonTmpGameServer getServer(int port) {
        DbNonTmpGameServer server = new DbNonTmpGameServer(this.databaseConnector, port, this.tableName);
        return server.exists() ? server : null;
    }
}
