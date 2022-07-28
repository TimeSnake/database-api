package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;

public interface DbBuildServer extends DbTaskServer {

    @Override
    Type.Server<DbBuildServer> getType();
}
