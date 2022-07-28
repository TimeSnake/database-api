package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Type;

public interface DbLobbyServer extends DbServer {

    @Override
    Type.Server<DbLobbyServer> getType();
}
