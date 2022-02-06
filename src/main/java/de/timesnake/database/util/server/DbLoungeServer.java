package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;

public interface DbLoungeServer extends DbTaskServer {

    DbTempGameServer getTwinServer();

    @Override
    Type.Server getType();
}
