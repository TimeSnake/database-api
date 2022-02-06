package de.timesnake.database.util.server;

public interface DbGameServer extends DbTaskServer {

    String getGameInfo();

    void setGameInfo(String info);
}
