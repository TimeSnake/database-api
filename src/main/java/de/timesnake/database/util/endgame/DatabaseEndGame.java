package de.timesnake.database.util.endgame;

import de.timesnake.database.util.server.DbNonTmpGameServer;

import java.util.UUID;

public interface DatabaseEndGame {

    DbEndGameUser getUser(UUID uuid);

    boolean containsUser(UUID uuid);

    void addUser(UUID uuid, String name, DbNonTmpGameServer server);

    void removeUser(UUID uuid);

    UUID getUserFromServer(DbNonTmpGameServer server);
}
