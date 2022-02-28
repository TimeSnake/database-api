package de.timesnake.database.core.server;

import de.timesnake.database.core.Column;
import de.timesnake.database.util.object.DatabaseConnector;

public class TempGameTable extends PvPTable<DbTempGameServer> {

    public TempGameTable(DatabaseConnector databaseConnector, String nameTable) {
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

    public void backup() {
        Column<?>[] columns = {Column.Server.PORT, Column.Server.NAME, Column.Server.PASSWORD, Column.Server.TASK};
        super.createBackup(columns);
    }

    @Override
    public DbTempGameServer getServer(int port) {
        DbTempGameServer server = new DbTempGameServer(this.databaseConnector, port, this.tableName);
        return server.exists() ? server : null;
    }

}
