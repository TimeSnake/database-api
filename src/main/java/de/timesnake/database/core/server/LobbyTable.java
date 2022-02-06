package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;

public class LobbyTable extends ServerTable<DbLobbyServer> {

    public LobbyTable(DatabaseConnector databaseConnector, String nameTable) {
        super(databaseConnector, nameTable);
    }

    @Override
    public DbLobbyServer getServer(int port) {
        DbLobbyServer server = new DbLobbyServer(this.databaseConnector, port, this.tableName);
        return server.exists() ? server : null;
    }
}
