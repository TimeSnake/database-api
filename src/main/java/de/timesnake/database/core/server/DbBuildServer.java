package de.timesnake.database.core.server;

import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.Type;

public class DbBuildServer extends DbTaskServer implements de.timesnake.database.util.server.DbBuildServer {

    public DbBuildServer(DatabaseConnector databaseConnector, Integer port, String nameTable) {
        super(databaseConnector, port, nameTable);
    }

    @Override
    public Type.Server<de.timesnake.database.util.server.DbBuildServer> getType() {
        return Type.Server.BUILD;
    }
}
