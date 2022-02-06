package de.timesnake.database.util.endgame;

import de.timesnake.database.util.server.DbGameServer;

import java.util.UUID;

public interface DbEndGameUser {

    boolean exists();

    void delete();

    UUID getUniqueId();

    String getName();

    DbGameServer getServer();

    void setServer(DbGameServer server);
}
