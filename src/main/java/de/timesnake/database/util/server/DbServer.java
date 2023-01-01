/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.server;

import de.timesnake.database.util.object.TooLongEntryException;
import de.timesnake.database.util.object.Type;
import de.timesnake.library.basic.util.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public interface DbServer {

    void delete();

    boolean exists();

    @NotNull
    String getName();

    void setName(String name);

    Integer getPort();

    @NotNull
    Status.Server getStatus();

    void setStatus(Status.Server status);

    @Nullable
    Integer getOnlinePlayers();

    void setOnlinePlayers(int playersOnline);

    @Nullable
    Integer getMaxPlayers();

    void setMaxPlayers(int playersMax);

    void setStatusSynchronized(Status.Server status);

    void setOnlinePlayersSynchronized(int playersOnline);

    @NotNull
    Type.Server<?> getType();

    @Nullable
    String getPassword();

    void setPassword(String password) throws TooLongEntryException;

    boolean hasPassword();

    @NotNull
    Path getFolderPath();
}
