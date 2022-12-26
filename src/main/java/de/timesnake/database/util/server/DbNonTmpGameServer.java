/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.database.util.server;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface DbNonTmpGameServer extends DbTaskServer {

    @Nullable
    String getGameInfo();

    void setGameInfo(String info);

    @Nullable
    UUID getOwnerUuid();

    void setOwnerUuid(UUID uuid);

    boolean hasOwner();
}
