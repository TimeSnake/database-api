package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;
import org.jetbrains.annotations.Nullable;

public class LobbyTable extends ServerTable<DbLobbyServer> {

    public LobbyTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
    }

    @Nullable
    @Override
    public DbLobbyServer getServer(int port) {
        DbLobbyServer server = new DbLobbyServer(this.databaseConnector, port, this.tableName);
        return server.exists() ? server : null;
    }
}
