package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public class TmpGameTable extends PvPTable<DbTmpGameServer> {

    public TmpGameTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
        super.addColumn(Column.Server.MAP_NAME);
        super.addColumn(Column.Server.MAPS);
        super.addColumn(Column.Server.KITS);
        super.addColumn(Column.Server.TEAM_AMOUNT);
        super.addColumn(Column.Server.TEAM_MAX_PLAYERS);
        super.addColumn(Column.Server.TEAM_MERGING);
        super.addColumn(Column.Server.TWIN_SERVER);
        super.addColumn(Column.Server.DISCORD);
    }

    @Override
    public void backup() {
        super.dropTmpTable();
    }

    @Override
    public DbTmpGameServer getServer(int port) {
        DbTmpGameServer server = new DbTmpGameServer(this.databaseConnector, port, this.tableName);
        return server.exists() ? server : null;
    }

}