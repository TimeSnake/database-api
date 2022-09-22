package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

public class LoungeTable extends TaskTable<DbLoungeServer> {

    public LoungeTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
    }

    @Override
    public void backup() {
        this.dropTmpTable();
    }

    @Nullable
    @Override
    public DbLoungeServer getServer(int port) {
        DbLoungeServer server = new DbLoungeServer(this.databaseConnector, port, this.tableName);
        return server.exists() ? server : null;
    }
}
