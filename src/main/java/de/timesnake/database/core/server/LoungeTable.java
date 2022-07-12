package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;

public class LoungeTable extends TaskTable<DbLoungeServer> {

    public LoungeTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
    }

    @Override
    public void backup() {
        this.dropTmpTable();
    }

    @Override
    public DbLoungeServer getServer(int port) {
        return new DbLoungeServer(this.databaseConnector, port, this.tableName);
    }
}
