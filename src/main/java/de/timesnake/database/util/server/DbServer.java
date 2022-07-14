package de.timesnake.database.util.server;

import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;

import java.nio.file.Path;

public interface DbServer {

    void delete();

    boolean exists();

    String getName();

    void setName(String name);

    Integer getPort();

    Status.Server getStatus();

    void setStatus(Status.Server status);

    Integer getOnlinePlayers();

    void setOnlinePlayers(int playersOnline);

    Integer getMaxPlayers();

    void setMaxPlayers(int playersMax);

    void setStatusSynchronized(Status.Server status);

    void setOnlinePlayersSynchronized(int playersOnline);

    Type.Server<?> getType();

    String getPassword();

    void setPassword(String password) throws TooLongEntryException;

    boolean hasPassword();

    Path getFolderPath();
}
