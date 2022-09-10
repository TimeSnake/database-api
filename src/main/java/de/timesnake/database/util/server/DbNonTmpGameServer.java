package de.timesnake.database.util.server;

import java.util.UUID;

public interface DbNonTmpGameServer extends DbTaskServer {

    String getGameInfo();

    void setGameInfo(String info);

    UUID getOwnerUuid();

    void setOwnerUuid(UUID uuid);

    boolean hasOwner();
}
