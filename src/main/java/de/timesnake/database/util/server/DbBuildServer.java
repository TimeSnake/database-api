package de.timesnake.database.util.server;

import de.timesnake.database.util.object.SyncExecute;
import de.timesnake.database.util.object.Type;

import java.util.Collection;

public interface DbBuildServer extends DbTaskServer {

    @Override
    Type.Server<DbBuildServer> getType();

    Collection<String> getWorldNames();

    void addWorld(String worldName);

    void removeWorld(String worldName);

    void clearWorlds();

    void clearWorlds(SyncExecute syncExecute);
}
