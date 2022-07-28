package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;

public interface DbLoungeServer extends DbTaskServer {

    DbTmpGameServer getTwinServer();

    @Override
    Type.Server<DbLoungeServer> getType();
}
