package de.timesnake.database.util.server;

public interface DbNonTmpGameServer extends DbTaskServer {

    String getGameInfo();

    void setGameInfo(String info);
}
