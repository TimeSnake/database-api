package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;

public class DbLobbyServer extends DbServer implements de.timesnake.database.util.server.DbLobbyServer {

    public DbLobbyServer(DatabaseConnector databaseConnector, Integer port, String nameTable) {
        super(databaseConnector, port, nameTable);
    }

    @Override
    public Type.Server getType() {
        return Type.Server.LOBBY;
    }
}
