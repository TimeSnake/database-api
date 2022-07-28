package de.timesnake.database.util.endgame;

import de.timesnake.database.util.server.DbNonTmpGameServer;

import java.util.UUID;

public interface DbEndGameUser {

    boolean exists();

    void delete();

    UUID getUniqueId();

    String getName();

    DbNonTmpGameServer getServer();

    void setServer(DbNonTmpGameServer server);
}
