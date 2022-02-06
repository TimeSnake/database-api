package de.timesnake.database.util.server;

import de.timesnake.database.util.object.Status;
import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.object.Type;

public interface DbServer {

    void delete();

    boolean exists();

    String getName();

    Integer getPort();

    Status.Server getStatus();

    Integer getOnlinePlayers();

    Integer getMaxPlayers();

    void setName(String name);

    void setStatus(Status.Server status);

    void setStatusSynchronized(Status.Server status);

    void setOnlinePlayers(int playersOnline);

    void setOnlinePlayersSynchronized(int playersOnline);

    void setMaxPlayers(int playersMax);

    Type.Server getType();

    void setPassword(String password) throws TooLongEntryException;

    String getPassword();

    boolean hasPassword();
}
