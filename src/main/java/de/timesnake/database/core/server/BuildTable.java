package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;

public class BuildTable extends TaskTable<DbBuildServer> {

    public BuildTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
    }

    @Override
    public DbBuildServer getServer(int port) {
        DbBuildServer server = new DbBuildServer(this.databaseConnector, port, this.tableName);
        return server.exists() ? server : null;
    }

    @Override
    public void backup() {
        super.dropTmpTable();
    }
}
