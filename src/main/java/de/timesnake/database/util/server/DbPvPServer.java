package de.timesnake.database.util.server;

public interface DbPvPServer extends DbTaskServer {

    boolean isOldPvP();

    void setPvP(boolean oldPvP);
}
