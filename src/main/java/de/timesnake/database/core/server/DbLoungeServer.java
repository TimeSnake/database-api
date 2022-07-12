package de.timesnake.database.core.server;

import de.timesnake.database.util.Database;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;
import de.timesnake.database.util.server.DbTempGameServer;

public class DbLoungeServer extends DbTaskServer implements de.timesnake.database.util.server.DbLoungeServer {

    public DbLoungeServer(DatabaseConnector databaseConnector, Integer port, String nameTable) {
        super(databaseConnector, port, nameTable);
    }

    @Override
    public de.timesnake.database.util.server.DbTempGameServer getTwinServer() {
        for (DbTempGameServer server : Database.getServers().getServers(Type.Server.TEMP_GAME)) {
            if (server.getTwinServerPort() != null && server.getTwinServerPort().equals(super.getPort())) {
                return server;
            }
        }
        return null;
    }

    @Override
    public Type.Server getType() {
        return Type.Server.LOUNGE;
    }
}
